package com.example.demo.service;

import com.example.demo.models.Team;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
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


    public Optional<Team> getRandomAvailableTeam(Integer wins) {
        return availableTeams.stream().filter(t -> Objects.equals(t.getWins(), wins)).findAny();
    }


}
