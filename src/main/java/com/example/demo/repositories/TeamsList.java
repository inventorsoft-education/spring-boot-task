package com.example.demo.repositories;

import com.example.demo.entities.Team;

import java.util.*;

public class TeamsList {
    Map<Integer,Team> teams = new HashMap<>();
  public   Integer points = (int)Math.random();

    public Team add(Integer points ,Team team){
        return teams.put(points , team);
    }

    public void size(){
        if(teams.size()%2==0){
            System.out.println(teams.size());
        } else {
            System.out.println("Amount of teams must be even. Pleas add or drop one team! ");
        }
    }

    public Integer getPoints() {
        return points;
    }


}

