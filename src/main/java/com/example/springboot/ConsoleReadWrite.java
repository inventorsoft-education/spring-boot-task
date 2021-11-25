package com.example.springboot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsoleReadWrite {

    Tournament tournament;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public ConsoleReadWrite(Tournament tournament) {
        this.tournament = tournament;
    }

    public void printMatches() {
        System.out.println("Round, Team 1, Team 2, Score");
        for (Match match : tournament.getMatchList().getMatchList()) {
            System.out.println(match);
        }
    }

    public void start() {
        String answer;
        System.out.println("        WELCOME TO THE TOURNAMENT");
        System.out.print("Do you want to add new teams?  (y / n)");
        answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            addTeam();
        }
            tournament.generateMatches();
            printMatches();
            System.out.println("WINNER - " + tournament.getWinner());
    }

        void addTeam() {
            System.out.println("Enter team information");
            boolean addNext = true;
            while (addNext) {
                Team team = new Team();
                System.out.print("Enter team name - ");
                team.setName(scanner.nextLine());
                System.out.print("Enter team captain - ");
                team.setCapitan(scanner.nextLine());
                System.out.print("Enter team coach - ");
                team.setCoach(scanner.nextLine());
                tournament.getTeamList().addTeam(team);
                System.out.println("You've added new team:\n" + team);
                if (!tournament.isPowerOfTwo(tournament.getTeamList().getSize())) {
                    System.out.println("Add more teams");
                } else {
                    addNext = false;
                }
            }

    }

}
