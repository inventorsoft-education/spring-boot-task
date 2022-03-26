package com.tournament.cup.settings;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SourceClass {
    TournamentClass tournamentClass;
    public void round() {
        System.out.print("Enter number of teams: ");
    Scanner scanner = new Scanner(System.in);
    boolean loop = true;
    do {
        int a = scanner.nextInt();
        if (a == 4) {
            tournamentClass.four();
            loop = false;
        } else if (a == 8) {
            tournamentClass.eight();
            loop = false;
        }
        else if (a == 16) {
            tournamentClass.sixteen();
            loop = false;
        }
        else {
            System.out.println("Number of teams should be 4, 8 or 16");
        }
    }while (loop);
    }
}