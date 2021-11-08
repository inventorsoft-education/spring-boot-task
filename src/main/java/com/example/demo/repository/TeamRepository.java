package com.example.demo.repository;

import com.example.demo.model.Team;
import com.example.demo.tournament.CSVcontroller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamRepository implements BaseTeamRepository {

    private static final List<Team> teams;

    static {
        teams = new ArrayList<>();
        CSVcontroller.Read(teams);
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
        CSVcontroller.Read(teams);
    }

    public void writeToFile() {
        CSVcontroller.Write(teams);
    }

    public void print() {
        System.out.println("List of commands:\n");
        for (Team team : teams) {
            System.out.println("Team name: " + team.getName());
            System.out.println("Team capitan: " + team.getCapitan());
            System.out.println("Team coach: " + team.getCoach() + "\n");

        }
    }
    @Override
    public boolean remove(Team team){
        teams.remove(team);
        return true;
    }

    public boolean checkValidCntOfTeams(){
        return teams.size() % 2 == 0;
    }


}
