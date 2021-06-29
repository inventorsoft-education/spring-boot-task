package co.inventrosoft.springboottask;

import co.inventrosoft.springboottask.console.ConsoleParser;
import co.inventrosoft.springboottask.console.MatchResult;
import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;
import co.inventrosoft.springboottask.service.MatchService;
import co.inventrosoft.springboottask.service.TeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SpringBootTaskApplication implements CommandLineRunner {
    private final MatchService matchService;
    private final TeamService teamService;

    private final ConsoleParser consoleParser;

    public SpringBootTaskApplication(MatchService matchService, ConsoleParser consoleParser, TeamService teamService) {
        this.matchService = matchService;
        this.teamService = teamService;
        this.consoleParser = consoleParser;
    }

    @Override
    public void run(String... args) throws Exception {

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
            if (!teamService.isTeamExist(matchResult.getFirstTeamName())) {
                consoleParser.printLine("team 1 does not exist");
                continue;
            }
            if (!teamService.isTeamExist(matchResult.getSecondTeamName())){
                consoleParser.printLine("team 2 does not exist");
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
            if (match.getRoundCode() == 1) {
                winner = match.getWinner();
            }
        } while (winner == null);
        consoleParser.printWinner(winner);
        matchService.writeAllToCsv();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskApplication.class, args);
    }

}
