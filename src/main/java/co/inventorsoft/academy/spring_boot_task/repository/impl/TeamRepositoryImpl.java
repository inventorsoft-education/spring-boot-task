package co.inventorsoft.academy.spring_boot_task.repository.impl;

import co.inventorsoft.academy.spring_boot_task.model.Team;
import co.inventorsoft.academy.spring_boot_task.repository.TeamRepository;
import co.inventorsoft.academy.spring_boot_task.repository.TournamentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(TeamRepositoryImpl.class);
    @Value("${tournament.teams}")
    private String teamsPath;
    @Value("${winners}")
    String winnerPath;

    @Override
    public void createTeam(Team team) {
        try (FileWriter csvWriter = new FileWriter(teamsPath, true)) {
            csvWriter.append(String.join(", ", TournamentUtils.convertTeamToListOfString(team)));
            csvWriter.append("\n");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> findAllTeams() {
        return TournamentUtils.getTeams(teamsPath);
    }

    @Override
    public void writeWinners(List<Team> winners) {
        TournamentUtils.writeListTeamsToFile(winnerPath, winners);
    }

    @Override
    public List<Team> readWinners() {
        return TournamentUtils.getTeams(winnerPath);
    }

}
