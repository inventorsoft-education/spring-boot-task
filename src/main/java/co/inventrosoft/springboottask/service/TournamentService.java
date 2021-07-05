package co.inventrosoft.springboottask.service;

import co.inventrosoft.springboottask.console.ConsoleParser;
import co.inventrosoft.springboottask.console.MatchResult;
import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class TournamentService {
    private final MatchService matchService;
    private final TeamService teamService;

    private final ConsoleParser consoleParser;

    public TournamentService(MatchService matchService, TeamService teamService, ConsoleParser consoleParser) {
        this.matchService = matchService;
        this.teamService = teamService;
        this.consoleParser = consoleParser;
    }

    public void start() throws IOException {
        // step 1: get teams
        List<Team> teams = consoleParser.getTeams();
        teamService.save(teams);
        Collections.shuffle(teams);

        // step 2: create tournament
        matchService.createTournament(teams);
        Team winner = null;
        do {
            consoleParser.printTournament(matchService.getTournament());

            MatchResult matchResult = consoleParser.getResultOfMatch();
            if (!matchService.areTeamsInMatchResultExists(matchResult)) {
                consoleParser.printLine("Teams are wrong!");
                continue;
            }
            Match match = matchService.getMatchByResult(matchResult);
            if (match == null) {
                consoleParser.printLine("Match does not exist!");
                continue;
            }
            if (match.getPlayed()) {
                consoleParser.printLine("Match was played already!");
                continue;
            }
            // update match here
            matchService.updateMatchResults(matchResult, match);
            if (match.isFinal()) {
                winner = match.getWinner();
            }
        } while (winner == null);
        consoleParser.printWinner(winner);
        matchService.writeAllToCsv();

    }
}
