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

    public String getPath() {
        return path;
    }
}
