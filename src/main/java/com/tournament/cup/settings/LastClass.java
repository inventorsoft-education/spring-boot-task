package com.tournament.cup.settings;

import com.tournament.cup.play.TeamsList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LastClass {

    private TeamsList teamsList;

    public void winnerOfTournament() {
        String winnerSixteen = teamsList.getTeamByIndex(0).getTeamName();
        System.out.println("\n____________Congratulations " + winnerSixteen);
        System.out.println("Thank you for playing");
        System.exit(0);
    }


}
