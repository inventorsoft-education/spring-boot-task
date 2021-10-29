package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tournament {
    private static  List<Team> winners = new ArrayList<>();
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    public static void start(){
        List<Team> allTeams = CreateTeams.makeAllTeams();

        findWinner(allTeams,winners);
        System.out.println(allTeams);
        WriteToFile.close();
    }

    private static void findWinner(List<Team> allTeams,List<Team> winners) {

        String round = "1/"+ allTeams.size()/2;
        System.out.println(round );
        firstRound(allTeams,winners,round);
        while (winners.size()>1){
            round = "1/"+ winners.size()/2;
            System.out.println(round);
            allTeams.addAll(winners);
            winners.clear();
            firstRound(allTeams,winners,round);
        }
        System.out.println("WINNER "+winners.get(0).getName());



    }



    private static void firstRound(List<Team> allTeams, List<Team> winners,String round) {
        while(allTeams.size()>0){

            Team firsTeam = allTeams.get(random.nextInt(allTeams.size()));
            allTeams.remove(firsTeam);
            Team secondTeam = allTeams.get(random.nextInt(allTeams.size()));
            allTeams.remove(secondTeam);

            System.out.println(firsTeam.getName() + " : " + secondTeam.getName());
            System.out.println("Enter score like 1:0");
            String score =scanner.nextLine();
            WriteToFile.writeDate(round,firsTeam.getName(),secondTeam.getName(),score);
            String[] subScore = score.split(":");
            if(Integer.parseInt(subScore[0])>Integer.parseInt(subScore[1])){
                winners.add(firsTeam);
            }else winners.add(secondTeam);
        }
        System.out.println("Winners: "+ winners);
    }
}
