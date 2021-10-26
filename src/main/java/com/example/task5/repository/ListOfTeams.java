package com.example.task5.repository;

import com.example.task5.model.Team;
import java.util.ArrayList;
import java.util.List;

public class ListOfTeams  {
    List<Team> teams;
    DataStore dataStore;

    public ListOfTeams(DataStore dataStore){
        teams = new ArrayList<>();
        this.dataStore = dataStore;
    }

    public void upData(){
        dataStore.setData((ArrayList<Team>) teams);
    }
    public void setTeams() {
        teams = dataStore.getData(new ArrayList<Team>());
    }

    public boolean add(Team team){ return teams.add(team);}

    public ArrayList<Team> get(){
        return new ArrayList<>(teams);
    }

    public int size(){
        return teams.size();
    }

}
