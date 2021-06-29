package co.inventorsoft.academy.spring_boot_task.repository;

import co.inventorsoft.academy.spring_boot_task.model.Team;
import java.util.List;

public interface TournamentRepository {

    void writeRandomTournament(List<Team> teams);
    List<Team> findSortedTournamentTeams();
}
