package com.tournament.cup.play;

import com.tournament.cup.details.Squad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class Tournament {

    private TeamsListFour teamsListFour;

    public String getWinnerFour(Squad squadFirstF, Squad squadSecondF, String four) {

        String resultFour = four + ", " + squadFirstF.getTeamName() + ",  " + squadSecondF.getTeamName() + ", " + squadFirstF.getPoints() + " : " + squadSecondF.getPoints();
        if (squadFirstF.getPoints() == squadSecondF.getPoints()) {
            teamsListFour.deleteTeamFour(squadFirstF);
        } else if (squadFirstF.getPoints() > squadSecondF.getPoints()) {
            teamsListFour.deleteTeamFour(squadSecondF);
        } else if (squadFirstF.getPoints() < squadSecondF.getPoints()) {
            teamsListFour.deleteTeamFour(squadFirstF);
        }
        return resultFour;
    }

    private TeamsListEight teamsListEight;

    public String getWinnerEight(Squad squadFirstE, Squad squadSecondE, String eight) {

        String resultEight = eight + ", " + squadFirstE.getTeamName() + ",  " + squadSecondE.getTeamName() + ", " + squadFirstE.getPoints() + " : " + squadSecondE.getPoints();
        if (squadFirstE.getPoints() == squadSecondE.getPoints()) {
            teamsListEight.deleteTeamEight(squadFirstE);
        } else if (squadFirstE.getPoints() > squadSecondE.getPoints()) {
            teamsListEight.deleteTeamEight(squadSecondE);
        } else if (squadFirstE.getPoints() < squadSecondE.getPoints()) {
            teamsListEight.deleteTeamEight(squadFirstE);
        }
        return resultEight;
    }

    private TeamsListSixteen teamsListSixteen;

    public String getWinnerSixteen(Squad squadFirstS, Squad squadSecondS, String sixteen) {

        String resultSixteen = sixteen + ", " + squadFirstS.getTeamName() + ",  " + squadSecondS.getTeamName() + ", " + squadFirstS.getPoints() + " : " + squadSecondS.getPoints();
        if (squadFirstS.getPoints() == squadSecondS.getPoints()) {
            teamsListSixteen.deleteTeamSixteen(squadFirstS);
        } else if (squadFirstS.getPoints() > squadSecondS.getPoints()) {
            teamsListSixteen.deleteTeamSixteen(squadSecondS);
        } else if (squadFirstS.getPoints() < squadSecondS.getPoints()) {
            teamsListSixteen.deleteTeamSixteen(squadFirstS);
        }
        return resultSixteen;
    }


}

