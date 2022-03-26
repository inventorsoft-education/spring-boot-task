package com.tournament.cup.settings;

import com.tournament.cup.play.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentClass {
    TeamsListEight teamsListEight;
    Tournament tournament;
    LastClass lastClass;
    WriterFile writerFile;
    TeamsListFour teamsListFour;
    TeamsListSixteen teamsListSixteen;

    public void four() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nRound , Team 1, Team 2, Score");
        switch (teamsListFour.sizeFour()) {
            case 2:
                String finalResult = tournament.getWinnerFour(teamsListFour.getTeamByIndex(0), teamsListFour.getTeamByIndex(1), "Final");
                System.out.println(finalResult);
                writerFile.writer(finalResult);
                lastClass.winnerOfTournamentFour();
                break;
            case 4:
                String halfFinal1 = tournament.getWinnerFour(teamsListFour.getTeamByIndex(2), teamsListFour.getTeamByIndex(3), "1/2");
                String halfFinal2 = tournament.getWinnerFour(teamsListFour.getTeamByIndex(1), teamsListFour.getTeamByIndex(0), "1/2");
                System.out.println(halfFinal1);
                System.out.println(halfFinal2);
                writerFile.writer(halfFinal1 + "\n" + halfFinal2);
                break;
        }
        System.out.println("\nPlease choose number of option:\n" +
                "1.Next round \n" +
                "2.Logout");
        int b = scanner.nextInt();
        if (b == 1) {
            four();
        } else if (b == 2) {
            System.out.println("Farewell");
            System.exit(0);
        }
        else{
            System.out.println("Just continue");
            four();
        }


    }

    public void eight() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nRound , Team 1, Team 2, Score");
        switch (teamsListEight.sizeEight()) {
            case 2:
                String finalResult = tournament.getWinnerEight(teamsListEight.getTeamByIndex(0), teamsListEight.getTeamByIndex(1), "Final");
                System.out.println(finalResult);
                writerFile.writer(finalResult);
                lastClass.winnerOfTournamentEight();
                break;
            case 4:
                String halfFinal1 = tournament.getWinnerEight(teamsListEight.getTeamByIndex(2), teamsListEight.getTeamByIndex(3), "1/2");
                String halfFinal2 = tournament.getWinnerEight(teamsListEight.getTeamByIndex(1), teamsListEight.getTeamByIndex(0), "1/2");
                System.out.println(halfFinal1);
                System.out.println(halfFinal2);
                writerFile.writer(halfFinal1 + "\n" + halfFinal2);
                break;
            case 8:
                String quarterFinal1 = tournament.getWinnerEight(teamsListEight.getTeamByIndex(6), teamsListEight.getTeamByIndex(7), "1/4");
                String quarterFinal2 = tournament.getWinnerEight(teamsListEight.getTeamByIndex(4), teamsListEight.getTeamByIndex(5), "1/4");
                String quarterFinal3 = tournament.getWinnerEight(teamsListEight.getTeamByIndex(3), teamsListEight.getTeamByIndex(2), "1/4");
                String quarterFinal4 = tournament.getWinnerEight(teamsListEight.getTeamByIndex(0), teamsListEight.getTeamByIndex(1), "1/4");
                System.out.println(quarterFinal1);
                System.out.println(quarterFinal2);
                System.out.println(quarterFinal3);
                System.out.println(quarterFinal4);
                writerFile.writer(quarterFinal1 + "\n" + quarterFinal2 + "\n" + quarterFinal3 + "\n" + quarterFinal4);
                break;
        }

        System.out.println("\nPlease choose number of option:\n" +
                "1.Next round \n" +
                "2.Logout");
        int b = scanner.nextInt();
        if (b == 1) {
            eight();
        } else if (b == 2) {
            System.out.println("Farewell");
            System.exit(0);
        }
        else{
            System.out.println("Just continue");
            eight();
        }
    }



    public void sixteen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nRound , Team 1, Team 2, Score");
        switch (teamsListSixteen.sizeSixteen()) {
            case 2:
                String finalResultS = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(0), teamsListSixteen.getTeamByIndex(1), "Final");
                System.out.println(finalResultS);
                writerFile.writer(finalResultS);
                lastClass.winnerOfTournamentSixteen();
                break;
            case 4:
                String halfFinal1S = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(2), teamsListSixteen.getTeamByIndex(3), "1/2");
                String halfFinal2S = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(1), teamsListSixteen.getTeamByIndex(0), "1/2");
                System.out.println(halfFinal1S);
                System.out.println(halfFinal2S);
                writerFile.writer(halfFinal1S + "\n" + halfFinal2S);
                break;
            case 8:
                String quarterFinal1S = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(6), teamsListSixteen.getTeamByIndex(7), "1/4");
                String quarterFinal2S = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(4), teamsListSixteen.getTeamByIndex(5), "1/4");
                String quarterFinal3S = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(3), teamsListSixteen.getTeamByIndex(2), "1/4");
                String quarterFinal4S = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(0), teamsListSixteen.getTeamByIndex(1), "1/4");
                System.out.println(quarterFinal1S);
                System.out.println(quarterFinal2S);
                System.out.println(quarterFinal3S);
                System.out.println(quarterFinal4S);
                writerFile.writer(quarterFinal1S + "\n" + quarterFinal2S + "\n" + quarterFinal3S + "\n" + quarterFinal4S);
                break;
            case 16:
                String quarterFinal5 = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(6), teamsListSixteen.getTeamByIndex(7), "1/8");
                String quarterFinal6 = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(4), teamsListSixteen.getTeamByIndex(5), "1/8");
                String quarterFinal7 = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(3), teamsListSixteen.getTeamByIndex(2), "1/8");
                String quarterFinal8 = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(0), teamsListSixteen.getTeamByIndex(1), "1/8");
                String quarterFinal9 = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(6), teamsListSixteen.getTeamByIndex(7), "1/8");
                String quarterFinal10 = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(4), teamsListSixteen.getTeamByIndex(5), "1/8");
                String quarterFinal11 = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(3), teamsListSixteen.getTeamByIndex(2), "1/8");
                String quarterFinal12 = tournament.getWinnerSixteen(teamsListSixteen.getTeamByIndex(0), teamsListSixteen.getTeamByIndex(1), "1/8");
                System.out.println(quarterFinal5);
                System.out.println(quarterFinal6);
                System.out.println(quarterFinal7);
                System.out.println(quarterFinal8);
                System.out.println(quarterFinal9);
                System.out.println(quarterFinal10);
                System.out.println(quarterFinal11);
                System.out.println(quarterFinal12);
                writerFile.writer(quarterFinal5 + "\n" + quarterFinal6 + "\n" + quarterFinal7 + "\n" + quarterFinal8 + "\n" + quarterFinal9 + "\n" + quarterFinal10 + "\n" + quarterFinal11 + "\n" + quarterFinal12);
                break;

        }

        System.out.println("\nPlease choose number of option:\n" +
                "1.Next round \n" +
                "2.Logout");
        int b = scanner.nextInt();
        if (b == 1) {
            sixteen();
        } else if (b == 2) {
            System.out.println("Farewell");
            System.exit(0);
        }
        else{
            System.out.println("Just continue");
            sixteen();
        }
    }

















}


