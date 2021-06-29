package co.inventrosoft.springboottask.repository;

import co.inventrosoft.springboottask.model.Team;

import java.io.IOException;
import java.util.List;

public interface TeamRepository {
    void saveAll(List<Team> teams) throws IOException;
    void add(Team team) throws IOException;
    List<Team> findAll() throws IOException;
}
