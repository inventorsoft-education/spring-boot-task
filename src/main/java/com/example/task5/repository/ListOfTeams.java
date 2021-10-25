package com.example.task5.repository;

import com.example.task5.model.Team;
import java.util.ArrayList;
import java.util.List;

public class ListOfTeams {
    List<Team> teams;
    private String path;

    public ListOfTeams(String path){
        teams = new ArrayList<>();
        this.path = path;
    }

    public boolean add(Team team){ return teams.add(team);}

    public ArrayList<Team> get(){
        return new ArrayList<>(teams);
    }

    public int size(){
        return teams.size();
    }

    public void print(){
        System.out.println("**********************");
        System.out.println("TEAMS LIST");
        for (Team t: teams) {
            System.out.println(t);
        }
        System.out.println("**********************");
    }

    public String getPath() {
        return path;
    }
}
