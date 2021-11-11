package com.example.demo.repository;

import com.example.demo.model.Team;
import com.example.demo.CSV.CSVlibrary;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Value
public class TeamRepository implements BaseTeamRepository {

    List<Team> teams;
    CSVlibrary csv;

    public TeamRepository(CSVlibrary csv) {
        this.csv = csv;
        teams = new ArrayList<>();
        readFromFile();
    }

    @Override
    public List<Team> getList() {
        return teams;
    }

    @Override
    public boolean add(Team team) {
        teams.add(team);
        return true;
    }

    public void readFromFile() {
        csv.readTeam(teams);
    }

    public void writeToFile() {
        csv.writeTeam(teams);
    }

    public void writeToFile(List<Team> teams) {
        csv.writeTeam(teams);
    }


    @Override
    public boolean remove(Team team) {
        teams.remove(team);
        return true;
    }

    public static boolean checkValidCntOfTeams(List<Team> teams) {
        return teams.size() % 2 == 0;
    }


}
