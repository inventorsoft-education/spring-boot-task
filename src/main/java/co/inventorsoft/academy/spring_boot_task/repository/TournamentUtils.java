package co.inventorsoft.academy.spring_boot_task.repository;

import co.inventorsoft.academy.spring_boot_task.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TournamentUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(TournamentUtils.class);

    public static List<Team> getTeams(String path) {
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
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return teamList;
    }

    public static void writeListTeamsToFile(String fileName, List<Team> teamList) {
        try (FileWriter csvWriter = new FileWriter(fileName)) {
            for (Team team : teamList) {
                csvWriter.append(String.join(", ", TournamentUtils.convertTeamToListOfString(team)));
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static List<String> convertTeamToListOfString(Team team) {
        List<String> list = new ArrayList<>();
        list.add(team.getName());
        list.add(team.getCaptain());
        list.add(team.getCoach());
        return list;
    }

}
