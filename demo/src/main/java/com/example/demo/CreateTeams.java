package com.example.demo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreateTeams {

    public static List<Team> makeAllTeams(){
        String[] teams = {"firstTeam","secondTeam","thirdTeam","forthTeam","fifthTeam","sixthTeam","seventhTeam","eighthTeam"};
        String[] capitans = {"JonhCap","SamCAp","BobCap","willCap","JonhCap","SamCAp","BobCap","willCap"};
        String[] coach = {"OneBoss","TwoBoss","ThreeBoss","FourBoss","5Boss","6Boss","7Boss","8Boss"};
        ArrayList<Team> allTeams = new ArrayList<>();



        for(int i=0;i<=teams.length-1;i++){
            allTeams.add(new Team(teams[i],capitans[i],coach[i]));
        }

        for(Team currentTeam: allTeams){
            System.out.println(currentTeam.getName()+", "+currentTeam.getCapitan()+", " + currentTeam.getCoach());
        }
        return allTeams;

    }
}
