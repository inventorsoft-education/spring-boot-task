package com.example.demo.service;

import com.example.demo.models.Match;
import com.example.demo.models.Team;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private final List<Match> upcomingMatches;
    private final List<Match> playedMatches;
    private Random random = new Random();
    private int wonMatches = 2;
    private static final int FIRST_ELEMENT = 0;

    private final TeamService teamService;

    public MatchService(TeamService teamService,List<Match> upcomingMatches,List<Match> playedMatches ) {
        this.teamService = teamService;
        this.upcomingMatches = upcomingMatches;
        this.playedMatches = playedMatches;
    }


    public  Match playMatch() {
        Match match = upcomingMatches.get(FIRST_ELEMENT);
        String winnerName = random.nextBoolean()?match.getTeam1():match.getTeam2();

        Team winner =  teamService.getByName(winnerName);
        winner.setWins(winner.getWins() + 1);

        match.setResult(winner.getName() + " " +"wins");
        upcomingMatches.remove(0);
        playedMatches.add(match);

        if(winner.getWins() >= wonMatches) {
            System.out.println("Tournament is over!!!");
            System.out.println("Glory to " + winner.getName());
            System.exit(0);
        }
        createNewMatchIfPossible(winner);
        return match;
    }




    private  void createNewMatchIfPossible(Team winner) {
        Optional<Team> opponent = teamService.getRandomAvailableTeam(winner.getWins());
        if(opponent.isPresent()){
            Match match = new Match();
            match.setTeam1(winner.getName());
            match.setTeam2(winner.getName());
            upcomingMatches.add(match);
            teamService.deleteAvailableTeam(winner);
        }else {
            teamService.addAvailableTeam(winner);
        }
    }

    @SneakyThrows
    public List<Match> tossTeams() {
        while (teamService.hasAvailableTeam()) {
            Match match = new Match();

           Team team1 = teamService.getRandomAvailableTeam(0)
                   .orElseThrow(() -> new Exception("Team not found "));
           match.setTeam1(team1.getName());
           teamService.deleteAvailableTeam(team1);

            Team team2 = teamService.getRandomAvailableTeam(0)
                    .orElseThrow(() -> new Exception("Team not found "));
            match.setTeam2(team2.getName());
            teamService.deleteAvailableTeam(team2);

            upcomingMatches.add(match);
        }
        return upcomingMatches;
    }
}
