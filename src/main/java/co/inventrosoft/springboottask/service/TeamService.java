package co.inventrosoft.springboottask.service;

import co.inventrosoft.springboottask.model.Team;
import co.inventrosoft.springboottask.repository.TeamRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void save(List<Team> teams) throws IOException {
        teamRepository.saveAll(teams);
    }

    public boolean isTeamExist(String teamName) throws IOException {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().anyMatch(team -> team.getName().equals(teamName));
    }
}
