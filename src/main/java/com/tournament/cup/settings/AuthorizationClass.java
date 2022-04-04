package com.tournament.cup.settings;

import com.tournament.cup.details.Squad;
import com.tournament.cup.play.TeamsList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@AllArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorizationClass {
    LastClass lastClass;
    TournamentClass tournamentClass;
    TeamsList teamsList;

    public void register() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter name of Team: ");
        String teamName = in.nextLine();
        System.out.println("Team name is " + teamName);

        System.out.print("Enter captain's name: ");
        String captainName = in.nextLine();
        System.out.println("Captain's name is " + captainName);

        System.out.print("Enter coach's name: ");
        String coachName = in.nextLine();
        System.out.println("Coach's name is " + coachName);

        teamsList.add(new Squad(teamName, captainName, coachName));
        teamsList.madeList();


    }
}
