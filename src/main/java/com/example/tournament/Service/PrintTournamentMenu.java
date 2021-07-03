package com.example.tournament.Service;

import org.springframework.stereotype.Service;

@Service
public class PrintTournamentMenu {

    public void printMenu() {
        System.out.println("If you want to add team enter:-> 1");
        System.out.println("if you want to generate tournament matches enter:-> 2");
        System.out.println("if you want to generate result for matches enter:-> 3");
        System.out.println("if you want to continue generate matches enter:-> 4");
        System.out.println("if you want to see a winner enter:-> 5");

    }
}
