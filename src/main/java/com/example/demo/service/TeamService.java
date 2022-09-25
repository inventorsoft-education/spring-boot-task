package com.example.demo.service;

import com.example.demo.models.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class TeamService {
    private List<Team> availableTeams = new ArrayList<>();
    private Map<String, Team> allTeams = new HashMap<>();


    public void deleteAvailableTeam(Team team) {
        availableTeams.remove(team);
    }

    public void addAvailableTeam(Team team) {
        availableTeams.add(team);
    }

    public Team getByName(String teamName) {
        return allTeams.get(teamName);
    }

    public boolean hasAvailableTeam() {
        return !availableTeams.isEmpty();
    }

    public Team createTeam(Team team) {
        allTeams.put(team.getName(), team);
        availableTeams.add(team);
        return team;
    }

    public void deleteTeam(String teamName) {
        Team team = allTeams.get(teamName);
        if (!availableTeams.contains(team)) {

            throw new RuntimeException("I can't delete this team");
        }
        allTeams.remove(team.getName());
        availableTeams.remove(team);
    }

    public Map<String,Team> findAllTeams(){
        return allTeams;
    }

    public Optional<Team> getRandomAvailableTeam(Integer wins) {
        return availableTeams.stream().filter(t -> Objects.equals(t.getWins(), wins)).findAny();
    }


}
