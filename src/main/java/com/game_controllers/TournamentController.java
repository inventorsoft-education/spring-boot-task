package com.game_controllers;

import com.DAO.TournamentDAO;
import com.entity.Tournament;
import com.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@Service
public class TournamentController {
    private TournamentDAO tournamentDao;

    public void startTournament(List<Team> participants, int rounds) {
        Tournament tournament = new Tournament(participants, rounds);
        generalLoop:
        while (true) {
            System.out.println("Choose option\n1-Play 1 round\n2-Play all rounds\n3-Get current result" +
                    "\4-Save result\n5-Go back");
            int option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1:
                    if (tournament.getRounds() == 0) {
                        System.out.println("Tournament has ended.");
                        break;
                    }
                    tournament.playRound();
                    System.out.println("Round played.");
                    break;
                case 2:
                    if (tournament.getRounds() == 0) {
                        System.out.println("Tournament has ended.");
                        break;
                    }
                    tournament.endTournament();

                    break;
                case 3:
                    System.out.println(tournament.getStringResult());
                    break;
                case 4:
                    if (tournament.getRounds() != 0) {
                        System.out.println("Tournament hasn't ended yet.");
                        break;
                    }
                    tournamentDao.save(tournament);
                    break;
                case 5:
                    break generalLoop;
            }
        }

    }

    public void resultsViewer() {
        generalLoop:
        while (true) {
            System.out.println("Choose option\n1-List of tournaments\n2-Get tournament result\n" +
                    "3-Remove tournament result\n4-Go back");
            int option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1:
                    System.out.println(tournamentDao.getFileNames());
                    break;
                case 2:
                    System.out.println("Input match date");
                    String date = new Scanner(System.in).nextLine();
                    System.out.println(tournamentDao.getResults(date));
                    break;
                case 3:
                    System.out.println("Input match date");
                    String name = new Scanner(System.in).nextLine();
                    tournamentDao.removeResult(name);
                    break;
                case 4:
                    break generalLoop;
            }
        }
    }
}

