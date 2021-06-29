package co.inventorsoft.academy.spring_boot_task.service;

import co.inventorsoft.academy.spring_boot_task.model.Result;
import co.inventorsoft.academy.spring_boot_task.model.Team;

public interface TournamentService {

    void addTeam(Team team);
    void tossTeams(String quantityOfTeam);
    void writeGameResult(Result result);
    void printTournamentWinner();
    void printUpcomingMatches();

}
