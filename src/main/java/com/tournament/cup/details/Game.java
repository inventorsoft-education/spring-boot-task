package com.tournament.cup.details;

import lombok.Data;
@Data
public class Game {
    private Squad squadFirst;
    private Squad squadSecond;
    private String round;


    public Game(Squad squadFirst, Squad squadSecond) {
        this.squadFirst = squadFirst;
        this.squadSecond = squadSecond;
    }
}
