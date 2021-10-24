package com.example.task5.model;

import java.util.Objects;

public class Game {

    private Team teamFirst;
    private Team teamSecond;
    private String round;
    private String result;

    public Game(Team teamFirst, Team teamSecond, String round, String result) {
        this.teamFirst = teamFirst;
        this.teamSecond = teamSecond;
        this.round = round;
        this.result = result;
    }

    @Override
    public String toString() {
        return  round + " " + teamFirst.getName() +
                " & " + teamSecond.getName() + " " + result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public Team getTeamFirst() {
        return teamFirst;
    }

    public Team getTeamSecond() {
        return teamSecond;
    }

    public String getResult() {
        return result;
    }

    public Team getWinner(){
        String[] str = result.split(":");
        int[] ints = new int[str.length];
        for(int i = 0; i < str.length; i++)
            ints[i] = Integer.valueOf(str[i]);
        if(ints[0] == ints[1]) return teamFirst;
        return ints[0] > ints[1] ? teamFirst : teamSecond;
    }

    public String getRound(){
        return round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return teamFirst.equals(game.teamFirst) &&
                teamSecond.equals(game.teamSecond) &&
                round.equals(game.round) &&
                result.equals(game.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamFirst, teamSecond, round, result);
    }
}
