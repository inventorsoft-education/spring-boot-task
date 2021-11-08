package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Match {
    private Team firstTeam;
    private Team secondTeam;
    private String round;
    private String score;

    public Match(Team firstTeam, Team secondTeam, String round) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        setRound(round);
        this.score = String.valueOf(firstTeam.roll() +':' + secondTeam.roll());
    }
    public void setRound(String round){
        if((Integer.parseInt(round) > 4) || ((Integer.parseInt(round) < 1))) throw new IllegalArgumentException("round must be > 0 and < 5");
        else this.round = round + '/' + 4;;
    }
}
