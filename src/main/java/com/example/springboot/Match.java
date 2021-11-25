package com.example.springboot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Match {

    int round;
    Team team1;
    Team team2;
    String score;

    public Match(int round, Team team1, Team team2) {
        this.round = round;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Team getWinner() {
        Team winner;
        int score1 = (int) (Math.random() * 10);
        int score2 = (int) (Math.random() * 10);
        if (score1 == score2) {
            score1++;
        }
        winner = (score1 < score2) ? team1 : team2;
        score = score1 + ":" + score2;
        return winner;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "1/" + round + ", " + team1.getName() + ", " + team2.getName() + ", " + score;
    }

}
