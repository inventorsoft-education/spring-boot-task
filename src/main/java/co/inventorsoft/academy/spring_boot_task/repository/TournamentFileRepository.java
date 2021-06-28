package co.inventorsoft.academy.spring_boot_task.repository;

import co.inventorsoft.academy.spring_boot_task.model.Team;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TournamentFileRepository {
    @Value("${tournament.results}")
    private String results;
    @Value("${tournament.teams}")
    private String teamsPath;
    @Value("${tournament.toss.teams}")
    String randomTournament;
    @Value("${winners}")
    String winnerPath;

    public void createTeam(Team team) {
        try (FileWriter csvWriter = new FileWriter(teamsPath, true)) {
            csvWriter.append(String.join(", ", convertTeamToListOfString(team)));
            csvWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> convertTeamToListOfString(Team team) {
        List<String> list = new ArrayList<>();
        list.add(team.getName());
        list.add(team.getCaptain());
        list.add(team.getCouch());
        return list;
    }

    public List<Team> findAllTeams() {
        return getTeams(teamsPath);
    }

    private List<Team> getTeams(String path) {
        List<Team> teamList = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) {
            return teamList;
        }
        try (BufferedReader csvReader = new BufferedReader(new FileReader(file))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(", ");
                teamList.add(new Team(data[0], data[1], data[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamList;
    }

    public void writeRandomTournament(List<Team> teams) {
        try (FileWriter csvWriter = new FileWriter(randomTournament)) {
            for (Team team : teams) {
                csvWriter.append(String.join(", ", convertTeamToListOfString(team)));
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Team> findSortedTournamentTeams() {
        return getTeams(randomTournament);
    }

    public void writeWinner(List<Team> winners) {
        try (FileWriter csvWriter = new FileWriter(winnerPath)) {
            for (Team team : winners) {
                csvWriter.append(String.join(", ", convertTeamToListOfString(team)));
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Team> readWinners() {
        return getTeams(winnerPath);
    }

    public void writeResultToFile(List<String> list) {
        try (FileWriter csvWriter = new FileWriter(results,true)) {
            File file = new File(results);
            if (file.length() == 0) {
                csvWriter.append("Round");
                csvWriter.append(", ");
                csvWriter.append("Team 1");
                csvWriter.append(", ");
                csvWriter.append("Team 2");
                csvWriter.append(", ");
                csvWriter.append("Score");
                csvWriter.append("\n");
            }
            csvWriter.append(String.join(", ", list));
            csvWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
