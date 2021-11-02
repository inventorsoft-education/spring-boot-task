package com.example.demo.service;

import com.example.demo.repository.TeamsList;
import com.example.demo.repository.Tournament;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentScreen {
    TeamsList teamsList;
    Tournament tournament;
    LastScreen lastScreen;

    public void round() {
        teamsList.warning();
        System.out.println("\nRound , Team 1, Team 2, Score");
        switch (teamsList.size()) {
            case 2:
                tournament.getWinner(teamsList.getTeamByIndex(0), teamsList.getTeamByIndex(1), "Final");
                lastScreen.winnerOfTournament();
                break;
            case 4:
                tournament.getWinner(teamsList.getTeamByIndex(2), teamsList.getTeamByIndex(3), "1/2");
                tournament.getWinner(teamsList.getTeamByIndex(1), teamsList.getTeamByIndex(0), "1/2");
                break;
            case 8:
                tournament.getWinner(teamsList.getTeamByIndex(6), teamsList.getTeamByIndex(7), "1/4");
                tournament.getWinner(teamsList.getTeamByIndex(4), teamsList.getTeamByIndex(5), "1/4");
                tournament.getWinner(teamsList.getTeamByIndex(3), teamsList.getTeamByIndex(2), "1/4");
                tournament.getWinner(teamsList.getTeamByIndex(0), teamsList.getTeamByIndex(1), "1/4");
                break;
        }
        System.out.println("\n Please choose number of option:\n" +
                " 1. Next round \n" +
                " 2. Logout");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String option = reader.readLine();
            if (option.equals("1")) {
                round();
            } else if (option.equals("2")) {
                System.out.println("Good bye");
                System.exit(0);
            }
        } catch (
                IOException E) {
            System.out.println("WRONG INPUT");
        }
    }
}


