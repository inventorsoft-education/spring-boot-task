package co.inventorsoft.academy.spring_boot_task.repository;

import co.inventorsoft.academy.spring_boot_task.model.Team;
import java.util.List;

public interface TeamRepository {

    void createTeam(Team team);
    List<Team> findAllTeams();
    void writeWinners(List<Team> winners);
    List<Team> readWinners();
}
