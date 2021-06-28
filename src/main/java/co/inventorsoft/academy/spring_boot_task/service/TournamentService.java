package co.inventorsoft.academy.spring_boot_task.service;

import co.inventorsoft.academy.spring_boot_task.model.Result;
import co.inventorsoft.academy.spring_boot_task.model.Team;
import co.inventorsoft.academy.spring_boot_task.repository.TournamentFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class TournamentService {
    private final TournamentFileRepository tournamentFileRepository;

    public void addTeam(Team team) {
        tournamentFileRepository.createTeam(team);
    }

    public void tossTeams(String quantityOfTeam) {
        int amountOfTeams = Integer.parseInt(quantityOfTeam);
        List<Team> tournamentTeams = new ArrayList<>();
        List<Team> teams = tournamentFileRepository.findAllTeams();
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
        tournamentFileRepository.writeRandomTournament(tournamentTeams);
    }

    public void writeGameResult(Result result) {
        List<String> list = new ArrayList<>();
        list.add(result.getRound());
        list.add(result.getOne().getName());
        list.add(result.getTwo().getName());
        list.add(result.getGameResult());
        tournamentFileRepository.writeResultToFile(list);
        List<Team> winnerList = tournamentFileRepository.readWinners();
        winnerList.add(getWinner(result.getOne(), result.getTwo(), result.getGameResult()));
        tournamentFileRepository.writeWinner(winnerList);
        removeTeamsFromCurrentRoundWhenGameFinished(result.getOne(), result.getTwo());
    }

    /**
     * Method delete teams after they played in the current round.
     * When the round finished winners automatically go to another round.
     * It will last for when one team doesn't become the winner.
     */
    private void removeTeamsFromCurrentRoundWhenGameFinished(Team one, Team two) {
        List<Team> teamList = tournamentFileRepository.findSortedTournamentTeams();
        List<Team> winners = tournamentFileRepository.readWinners();
        if (teamList.size() == 2 && winners.size() > 1) {
            tournamentFileRepository.writeRandomTournament(winners);
            winners.clear();
            tournamentFileRepository.writeWinner(winners);
        } else if (teamList.size() > 1) {
            teamList.remove(one);
            teamList.remove(two);
            tournamentFileRepository.writeRandomTournament(teamList);
        }
    }

    public void  printTournamentWinner() {
        List<Team> winners = tournamentFileRepository.readWinners();
        List<Team> tournamentTeams = tournamentFileRepository.findSortedTournamentTeams();
        if (winners.size() == 1 && tournamentTeams.isEmpty()) {
            Team winner = winners.get(0);
            winners.clear();
            tournamentFileRepository.writeWinner(winners);
            System.out.println("Tournament winner is: " + winner);
        } else {
            System.out.println("Tournament haven't finished yet");
        }
    }

    public void printUpcomingMatches() {
        List<Team> teams = tournamentFileRepository.findSortedTournamentTeams();
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
