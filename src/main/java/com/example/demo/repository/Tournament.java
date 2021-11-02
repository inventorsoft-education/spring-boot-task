package com.example.demo.repository;

import com.example.demo.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class Tournament {
   private TeamsList teamsList;

    public void getWinner(Team teamFirst, Team teamSecond ,String round) {

        String result = teamFirst.getPoints() + " : " + teamSecond.getPoints();
        if (teamFirst.getPoints() == teamSecond.getPoints()) {
           teamsList.deleteTeam(teamFirst);
        }else if (teamFirst.getPoints() > teamSecond.getPoints()) {
          teamsList.deleteTeam(teamSecond);
        } else if (teamFirst.getPoints() < teamSecond.getPoints()) {
          teamsList.deleteTeam(teamFirst);
        }
        System.out.println(round + ", " + teamFirst.getTeamName() + ",  " + teamSecond.getTeamName() + ", " + result);
    }
}

