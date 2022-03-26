package com.tournament.cup.settings;

import com.tournament.cup.details.Squad;
import com.tournament.cup.play.TeamsListEight;
import com.tournament.cup.play.TeamsListFour;
import com.tournament.cup.play.TeamsListSixteen;
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
    TeamsListEight teamsListEight;
    TeamsListFour teamsListFour;
    TeamsListSixteen teamsListSixteen;

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

        teamsListSixteen.add(new Squad(teamName, captainName, coachName));
        teamsListSixteen.madeList();

        teamsListEight.add(new Squad(teamName, captainName, coachName));
        teamsListEight.madeList();

        teamsListFour.add(new Squad(teamName, captainName, coachName));
        teamsListFour.madeList();


    }
}
