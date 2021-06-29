package co.inventrosoft.springboottask.repository;

import co.inventrosoft.springboottask.model.Match;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;


@Repository
public class MatchFileRepository implements MatchRepository {
    private final static String matchesFile = "matches.json";
    private final ObjectMapper mapper;

    public MatchFileRepository(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public void saveAll(List<Match> matches) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(matchesFile, false)))) {
            mapper.writeValue(out, matches);
        }
    }
    @Override
    public List<Match> findAll() throws IOException {
        return mapper.readValue(new File(matchesFile), new TypeReference<>() {});
    }

    @Override
    public Match getByTeamNames(String firstTeamName, String secondTeamName) throws IOException {
        List<Match> matches = findAll();
        Match resultMatch = null;
        for (Match match: matches) {
            boolean checkIfTeamsNotNulls = match.getFirstTeam() != null && match.getSecondTeam() != null;
            if (checkIfTeamsNotNulls && match.getFirstTeam().getName().equals(firstTeamName)
                    && match.getSecondTeam().getName().equals(secondTeamName)) {
                resultMatch = match;
                break;
            }
        }
        return resultMatch;
    }

    @Override
    public Match getByRoundCodeAndOrder(int roundCode, int order) throws IOException {
        List<Match> matches = findAll();
        Match resultMatch = null;
        for (Match match: matches) {
            if (match.getRoundCode() == roundCode && match.getOrder() == order) {
                resultMatch = match;
                break;
            }
        }
        return resultMatch;
    }

    @Override
    public boolean save(Match match) throws IOException {
        List<Match> matches = findAll();
        boolean found = false;
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i).getRoundCode() == match.getRoundCode() && matches.get(i).getOrder() == match.getOrder()) {
                matches.set(i, match);
                found = true;
                saveAll(matches);
                break;
            }
        }
        return found;
    }


}
