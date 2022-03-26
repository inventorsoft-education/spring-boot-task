package com.tournament.cup.settings;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FirstClass {
    SourceClass sourceClass;
    TournamentClass tournamentClass;
    AuthorizationClass authorizationClass;

    public void mainScreen(){
        System.out.println("\n                                                   CHAMPIONS LEAGUE");
        System.out.println("                                                  PLAY IF YOU WORTHY     ");
        System.out.print("\nAre you sure you want to play?\n");
        System.out.print("Yes or No : ");
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        if (a.matches("Yes")) {
            System.out.println("I knew you are worthy");
        } else {
            System.out.println("Doesn't matter, you will play anyway");
        }

        authorizationClass.register();
        System.out.println("Choose your destiny:\n" +
                "1.Begin tournament \n" +
                "2.Exit tournament");

        int b = scanner.nextInt();
        if (b == 1) {
            sourceClass.round();
        } else if (b == 2) {
            System.out.println("No way you chose it, but ok. Farewell");
            System.exit(0);
        }
        else {
            System.out.println("Let's pretend you chose begin");
            sourceClass.round();

        }

    }
}

