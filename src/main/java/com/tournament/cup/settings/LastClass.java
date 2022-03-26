package com.tournament.cup.settings;

import com.tournament.cup.play.TeamsListEight;
import com.tournament.cup.play.TeamsListFour;
import com.tournament.cup.play.TeamsListSixteen;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LastClass {

    private TeamsListSixteen teamsListSixteen;
    private TeamsListEight teamsListEight;
    private TeamsListFour teamsListFour;

    public void winnerOfTournamentSixteen() {
        String winnerSixteen = teamsListSixteen.getTeamByIndex(0).getTeamName();
        System.out.println("\n____________Congratulations " + winnerSixteen);
        System.out.println("Thank you for playing");
        System.exit(0);
    }

    public void winnerOfTournamentEight() {
        String winnerEight = teamsListEight.getTeamByIndex(0).getTeamName();
        System.out.println("\n____________Congratulations " + winnerEight);
        System.out.println("Thank you for playing");
        System.exit(0);
    }

    public void winnerOfTournamentFour() {
        String winnerFour = teamsListFour.getTeamByIndex(0).getTeamName();
        System.out.println("\n____________Congratulations " + winnerFour);
        System.out.println("Thank you for playing");
        System.exit(0);
    }

}
