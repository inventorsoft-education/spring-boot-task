package co.inventorsoft.academy.spring_boot_task.repository.impl;

import co.inventorsoft.academy.spring_boot_task.model.Team;
import co.inventorsoft.academy.spring_boot_task.repository.TournamentRepository;
import co.inventorsoft.academy.spring_boot_task.repository.TournamentUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TournamentRepositoryImpl implements TournamentRepository {

    @Value("${tournament.toss.teams}")
    String randomTournament;

    @Override
    public void writeRandomTournament(List<Team> teams) {
        TournamentUtils.writeListTeamsToFile(randomTournament, teams);
    }

    @Override
    public List<Team> findSortedTournamentTeams() {
        return TournamentUtils.getTeams(randomTournament);
    }

}
