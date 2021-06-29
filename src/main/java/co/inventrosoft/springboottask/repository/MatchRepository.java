package co.inventrosoft.springboottask.repository;

import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;

import java.io.IOException;
import java.util.List;

public interface MatchRepository {
    void saveAll(List<Match> matches) throws IOException;
    List<Match> findAll() throws IOException;
    Match getByTeamNames(String firstTeam, String secondTeam) throws IOException;
    Match getByRoundCodeAndOrder(int roundCode, int order) throws IOException;
    boolean save(Match match) throws IOException;
}
