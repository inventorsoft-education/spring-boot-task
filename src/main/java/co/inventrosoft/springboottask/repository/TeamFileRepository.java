package co.inventrosoft.springboottask.repository;

import co.inventrosoft.springboottask.model.Match;
import co.inventrosoft.springboottask.model.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;

@Repository
public class TeamFileRepository implements TeamRepository{

    private final static String teamsFile = "teams.json";
    private final ObjectMapper mapper;

    public TeamFileRepository(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void saveAll(List<Team> teams) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(teamsFile, false)))) {
            mapper.writeValue(out, teams);
        }
    }

    @Override
    public void add(Team team) throws IOException {
        List<Team> teams = findAll();
        teams.add(team);
        saveAll(teams);
    }

    @Override
    public List<Team> findAll() throws IOException {
        return mapper.readValue(new File(teamsFile), new TypeReference<>() {});
    }
}
