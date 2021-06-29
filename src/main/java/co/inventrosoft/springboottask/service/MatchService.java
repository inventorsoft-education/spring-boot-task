package co.inventrosoft.springboottask.service;

import co.inventrosoft.springboottask.console.MatchResult;
import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;
import co.inventrosoft.springboottask.repository.MatchRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }
    /**
     * creates a tournament with matches,
     * where teams in first round stores teams from list, the rest stores null
     * @param teams stores teams
     */
    public void createTournament(List<Team> teams) throws IOException {
        int teamCount = teams.size();
        int roundCount = (int)(Math.log(teamCount) / Math.log(2)); // log base 2
        ArrayList<Match> matches = new ArrayList<>();

        for (int round = 0; round < roundCount; round++) {

            // teamCount / (2 ^ (round+1))
            // this is code of round. e.g code == 2 -> round name = 1/2
            int numOfMatchesInRound = (int)(teamCount / (Math.pow(2, round+1)));

            for (int matchOrder = 0; matchOrder < numOfMatchesInRound; matchOrder++) {
                Match match = new Match(numOfMatchesInRound, matchOrder);
                // if first round set teams to matches
                if (round == 0) {
                    match.setFirstTeam(teams.get(matchOrder * 2));
                    match.setSecondTeam(teams.get(matchOrder * 2 + 1));
                }
                matches.add(match);
            }
        }
        matchRepository.saveAll(matches);
    }

    /**
     * finds and sets match's score by MatchResult object
     * @param matchResult stores score and team names
     * @param match is a match to be updated
     */
    public void updateMatchResults(MatchResult matchResult, Match match) throws IOException {
        match.setFirstTeamResult(matchResult.getFirstTeamResult());
        match.setSecondTeamResult(matchResult.getSecondTeamResult());
        match.setPlayed(true);
        matchRepository.save(match);

        int roundCode = match.getRoundCode();
        int order = match.getOrder();

        // if not final
        if (roundCode != 1) {
            Team winner = match.getWinner();
            // find match where winner should be placed
            Match nextMatch = matchRepository.getByRoundCodeAndOrder(roundCode / 2, order / 2);

            nextMatch.setTeamByOrder(order, winner);
            matchRepository.save(nextMatch);
        }
    }

    public List<Match> getTournament() throws IOException {
        return matchRepository.findAll();
    }

    /**
     * finds in storage match by MatchResult object
     * swaps values in matchResult if match was not found
     * @param matchResult stores score and team names
     */
    public Match getMatchByResult(MatchResult matchResult) throws IOException {
        Match match = matchRepository.getByTeamNames(matchResult.getFirstTeamName(), matchResult.getSecondTeamName());
        if (match == null) {
            matchResult.swap();
            match = matchRepository.getByTeamNames(matchResult.getFirstTeamName(), matchResult.getSecondTeamName());
        }
        return match;
    }

    public void writeAllToCsv() throws IOException {
        List<Match> matches = matchRepository.findAll();
        try (PrintWriter writer = new PrintWriter("result.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append("Round,Team 1,Team 2,Score\n");
            for (Match match: matches) {
                sb.append(match.toCsv());
            }

            writer.write(sb.toString());



        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
