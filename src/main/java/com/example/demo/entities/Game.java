package com.example.demo.entities;

import com.example.demo.repositories.TeamsList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class Game {
    
    private Team teamFirst;
    private Team teamSecond;
    private String round;
    private String result;

    @Autowired
    public Game(Team teamFirst, Team teamSecond, String round, String result) {
        this.teamFirst = teamFirst;
        this.teamSecond = teamSecond;
        this.round = round;
        this.result = result;
    }

    public Team getWinner(){
        String[] str = result.split(":");
        int[] ints = new int[str.length];
        for(int i = 0; i < str.length; i++)
            ints[i] = Integer.valueOf(str[i]);
        if(ints[0] == ints[1]) return teamSecond;
        return ints[0] > ints[1] ? teamFirst : teamSecond;
    }



}
