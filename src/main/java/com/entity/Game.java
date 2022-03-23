package com.entity;

import lombok.Getter;

public class Game {
    private Team firstTeam, secondTeam;
    @Getter
    private int firstTeamScore=0,secondTeamScore=0;
    public int round;
    @Getter
    private String[] result;
    public Game(Team firstTeam, Team secondTeam,int round){
        this.firstTeam=firstTeam;
        this.secondTeam=secondTeam;
        this.round=round;
    }
    public Team play(){
        firstTeamScore=(int)(Math.random()*10);
        secondTeamScore=(int)(Math.random()*10);
        while (secondTeamScore==firstTeamScore){
            secondTeamScore=(int)(Math.random()*10);
        }
        result= new String[]{"1/" + (int) (Math.pow(2, round - 1)), firstTeam.getTeamName()
                , secondTeam.getTeamName(), firstTeamScore + ":" + secondTeamScore};
        return firstTeamScore>secondTeamScore ? firstTeam : secondTeam;
    }
}
