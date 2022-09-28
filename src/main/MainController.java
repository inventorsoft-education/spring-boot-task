package main;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.LinkedList;

@Controller
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {
    Tournament tournament;
    FileRW fileRW;

    @Autowired
    public MainController(Tournament tournament, FileRW fileRW) {
        this.tournament = tournament;
        this.fileRW = fileRW;
    }

    public void playTournament() {
        System.out.println("        WELCOME TO THE TOURNAMENT ");
        tournament.setTeamList(registerTeams());
        Team winner = tournament.start();
        System.out.println("Winner of tournament is : " + winner.toString());
        fileRW.writeMatch("src/main/races.csv", tournament.getRaceList());
    }
    private LinkedList<Team> registerTeams() {
        return fileRW.readTeamsFromFile("src/main/teams.csv");
    }
}
