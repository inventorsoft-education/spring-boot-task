package com.tournament.cup.play;

import com.tournament.cup.details.Squad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class Tournament {

    private TeamsList teamsList;

    public String getWinner(Squad squadFirstS, Squad squadSecondS, String sixteen) {

        String resultSixteen = sixteen + ", " + squadFirstS.getTeamName() + ",  " + squadSecondS.getTeamName() + ", " + squadFirstS.getPoints() + " : " + squadSecondS.getPoints();
        if (squadFirstS.getPoints() == squadSecondS.getPoints()) {
            teamsList.delete(squadFirstS);
        } else if (squadFirstS.getPoints() > squadSecondS.getPoints()) {
            teamsList.delete(squadSecondS);
        } else if (squadFirstS.getPoints() < squadSecondS.getPoints()) {
            teamsList.delete(squadFirstS);
        }
        return resultSixteen;
    }


}

