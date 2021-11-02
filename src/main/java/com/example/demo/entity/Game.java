package com.example.demo.entity;

import lombok.Data;
@Data
public class Game {
    private Team teamFirst;
    private Team teamSecond;
    private String round;


    public Game(Team teamFirst, Team teamSecond) {
        this.teamFirst = teamFirst;
        this.teamSecond = teamSecond;
    }

//    public Team getWinner(){
//        String[] str = result.split(":");
//        int[] ints = new int[str.length];
//        for(int i = 0; i < str.length; i++)
//            ints[i] = Integer.valueOf(str[i]);
//        if(ints[0] == ints[1]) return teamSecond;
//        return ints[0] > ints[1] ? teamFirst : teamSecond;
//    }



}
