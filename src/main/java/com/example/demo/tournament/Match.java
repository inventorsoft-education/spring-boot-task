package com.example.demo.tournament;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Getter
public class Match {
    private String round;
    private Team teamOne;
    private Team teamTwo;
    private String score;
    private ConsoleWriter consoleWriter;
    private Data data;


    public Match(Team teamOne, Team teamTwo,  String round, ConsoleWriter consoleWriter, Data data){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.round = round;
        this.consoleWriter = consoleWriter;
        this.data = data;
    }

    public Team getResult() {
        Map<Team, Integer> result = new HashMap<>();

        int teamOneScore = (int) (Math.random() * 5);
        int teamTwoScore = (int) (Math.random() * 5);

        if(teamOneScore == teamTwoScore){
            teamOneScore++;
        }

        result.put(teamOne, teamOneScore);
        result.put(teamTwo, teamTwoScore);

        setScore(result.get(teamOne), result.get(teamTwo));

        consoleWriter.printMatch(result, this);

        data.writeDataToFile(this.toString());

        if(result.get(teamOne) > result.get(teamTwo)){
            return teamOne;
        }
        else{
            return teamTwo;
        }
    }

    public void setScore(int teamOneResult, int teamTwoResult){
        this.score = teamOneResult + ":" + teamTwoResult;
    }

    @Override
    public String toString() {
        return "Round, Team1, Team2, Score\n"
                + round + ", " + teamOne.getName() + ", " + teamTwo.getName() + ", " + score;
    }
}
