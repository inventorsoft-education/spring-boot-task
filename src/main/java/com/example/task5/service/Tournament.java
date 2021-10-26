package com.example.task5.service;

import com.example.task5.model.Game;
import com.example.task5.model.Team;
import com.example.task5.repository.ListOfGames;
import com.example.task5.repository.ListOfTeams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class Tournament {

    private ListOfGames listOfGames;
    private ListOfTeams listOfTeams;
    private final Scanner scanner;
    private Team winner;
    private Console console;
    private boolean isUse = false;

    @Autowired
    Tournament(ListOfTeams listOfTeams, ListOfGames listOfGames, Scanner scanner, Console console){
        this.listOfTeams = listOfTeams;
        this.listOfGames = listOfGames;
        this.scanner = scanner;
        this.console = console;
    }

    public void register(){
        String name, captain, coach;
        Team team;

        boolean isContinue = true;
        while (isContinue) {
            System.out.print("Use saved teams data");
            if(isYes()){
                listOfTeams.setTeams();
                isUse = true;
                break;
            }else {
                System.out.println("**********************");
                System.out.println("NEW TEAM REGISTRATION");
                System.out.print("Enter team's name: ");
                name = scanner.nextLine();
                System.out.print("Enter team's captain: ");
                captain = scanner.nextLine();
                System.out.print("Enter team's coach: ");
                coach = scanner.nextLine();

                team = new Team(name, captain, coach);
                System.out.println(team);
                listOfTeams.add(team);

                if (isPowerOfTwo(listOfTeams.size()) && listOfTeams.size() >= 4) {
                    System.out.println("Add more teams");
                    isContinue = isYes();
                }
            }
        }

    }

    public void start() {
        List<Team> ttemp = listOfTeams.get();
        Game gtemp;
        Random random = new Random();
        int one, two;
        int rmax = (int) (Math.log(ttemp.size()) / Math.log(2));

        System.out.print("Use stored result");
        if (isUse && isYes()) listOfGames.setGames();
        else {
            for (int i = rmax; i >= 0; i--) {
                while (ttemp.size() >= 2) {

                    do {
                        one = random.nextInt(ttemp.size());
                        two = random.nextInt(ttemp.size());
                    } while (one <= two);

                    gtemp = new Game(
                            ttemp.get(one),
                            ttemp.get(two),
                            i != 1 ? "1/" + i : "final",
                            one + ":" + two
                    );

                    System.out.println(gtemp.getRound() + " " +
                            gtemp.getTeamFirst().getName() + " - " +
                            gtemp.getTeamSecond().getName());

                    System.out.print("Use default result: ");
                    if (!isYes()) {
                        System.out.print("Set result (exp 1:3) ");
                        gtemp.setResult(scanner.nextLine());
                    }
                    listOfGames.addGame(gtemp);
                    ttemp.remove(one);
                    ttemp.remove(two);
                }
                ttemp = listOfGames.getWinners("1/" + i);
            }

            ttemp = listOfGames.getWinners("final");
            for (Team t : ttemp) {
                winner = t;
            }

        }
    }
    public void printGamesTable() {
        console.printMap("GAME LIST", listOfGames.getGames());
    }

    public  void printTeamsTable(){
         console.printList("TEAMS LIST", listOfTeams.get());
    }

    public Team getWinner(){
        return winner;
    }

    public void resultsExport() {
        listOfTeams.upData();
        listOfGames.upData();
    }

    public void introduce(){
        System.out.println("***TOURNAMENT***");
        System.out.println("First you need to register teams");
        System.out.println("Then you need to set game's result");
    }

    private boolean isYes(){
        System.out.print("Y/N ? : ");
        return scanner.nextLine().equalsIgnoreCase("y");
    }

    private boolean isPowerOfTwo(int x){
        return x != 0 && ((x & (x - 1)) == 0);
    }

}
