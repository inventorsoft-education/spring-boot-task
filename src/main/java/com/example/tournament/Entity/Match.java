package com.example.tournament.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private String round;
    private Team team1;
    private Team team2;
    private String score;
    private Team winner;

}
