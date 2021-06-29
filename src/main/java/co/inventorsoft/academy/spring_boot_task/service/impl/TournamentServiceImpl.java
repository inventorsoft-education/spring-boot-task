package co.inventorsoft.academy.spring_boot_task.service.impl;

import co.inventorsoft.academy.spring_boot_task.model.Result;
import co.inventorsoft.academy.spring_boot_task.model.Team;
import co.inventorsoft.academy.spring_boot_task.repository.ResultRepository;
import co.inventorsoft.academy.spring_boot_task.repository.TeamRepository;
import co.inventorsoft.academy.spring_boot_task.repository.TournamentRepository;
import co.inventorsoft.academy.spring_boot_task.service.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepositoryImpl;
    private final TeamRepository teamRepositoryImpl;
    private final ResultRepository resultRepositoryImpl;

    @Override
    public void addTeam(Team team) {
        teamRepositoryImpl.createTeam(team);
    }

    @Override
    public void tossTeams(String quantityOfTeam) {
        int amountOfTeams = Integer.parseInt(quantityOfTeam);
        List<Team> tournamentTeams = new ArrayList<>();
        List<Team> teams = teamRepositoryImpl.findAllTeams();
        if (teams.size() < amountOfTeams) {
            throw new RuntimeException("Please create more teams");
        }
        Random random = new Random();
        while (tournamentTeams.size() < amountOfTeams) {
            int index = random.nextInt(teams.size());
            tournamentTeams.add(teams.get(index));
            teams.remove(index);
        }
        tournamentTeams.addAll(teams);
        tournamentRepositoryImpl.writeRandomTournament(tournamentTeams);
    }

    @Override
    public void writeGameResult(Result result) {
        List<String> list = new ArrayList<>();
        list.add(result.getRound());
        list.add(result.getOne().getName());
        list.add(result.getTwo().getName());
        list.add(result.getGameResult());
        resultRepositoryImpl.writeResultToFile(list);
        List<Team> winnerList = teamRepositoryImpl.readWinners();
        winnerList.add(getWinner(result.getOne(), result.getTwo(), result.getGameResult()));
        teamRepositoryImpl.writeWinners(winnerList);
        removeTeamsFromCurrentRoundWhenGameFinished(result.getOne(), result.getTwo());
    }

    /**
     * Method delete teams after they played in the current round.
     * When the round finished winners automatically go to another round.
     * It will last for when one team doesn't become the winner.
     */
    private void removeTeamsFromCurrentRoundWhenGameFinished(Team one, Team two) {
        List<Team> teamList = tournamentRepositoryImpl.findSortedTournamentTeams();
        List<Team> winners = teamRepositoryImpl.readWinners();
        if (teamList.size() == 2 && winners.size() > 1) {
            tournamentRepositoryImpl.writeRandomTournament(winners);
            winners.clear();
            teamRepositoryImpl.writeWinners(winners);
        } else if (teamList.size() > 1) {
            teamList.remove(one);
            teamList.remove(two);
            tournamentRepositoryImpl.writeRandomTournament(teamList);
        }
    }

    @Override
    public void  printTournamentWinner() {
        List<Team> winners = teamRepositoryImpl.readWinners();
        List<Team> tournamentTeams = tournamentRepositoryImpl.findSortedTournamentTeams();
        if (winners.size() == 1 && tournamentTeams.isEmpty()) {
            Team winner = winners.get(0);
            winners.clear();
            teamRepositoryImpl.writeWinners(winners);
            System.out.println("Tournament winner is: " + winner);
        } else {
            System.out.println("Tournament haven't finished yet");
        }
    }

    @Override
    public void printUpcomingMatches() {
        List<Team> teams = tournamentRepositoryImpl.findSortedTournamentTeams();
        if (teams.size() > 1) {
            for (int i = 0; i < teams.size(); i += 2) {
                System.out.println(teams.get(i).getName() + " : " + teams.get(i + 1).getName());
            }
        }
    }

    private Team getWinner(Team one, Team two, String result) {
        String[] s = result.split(":");
        int firstResult = Integer.parseInt(s[0]);
        int secondResult = Integer.parseInt(s[1]);
        return firstResult > secondResult ? one : two;
    }
}
