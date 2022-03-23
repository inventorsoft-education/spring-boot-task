package com.entity;

import lombok.Getter;

public class Team {
    @Getter
    private String teamName, coach, captain;

    public Team(String teamName,String captain,String coach){
        this.captain=captain;
        this.coach=coach;
        this.teamName=teamName;
    }
    public String toString(){
        return teamName+", "+coach+", "+captain;
    }
}
