package com.example.tournament.Service;

import com.example.tournament.Entity.Match;
import com.example.tournament.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class FileSaveService {

    private MapperService mapperService;

    @Autowired
    public FileSaveService(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    public void saveMatchesToFile(String fileName, List<Match> matchList) throws IOException {
        File f1 = new File("D:\\InventorSovtTasks\\TournaventWithSpring\\myObjects.txt");
        if (!f1.exists()) {
            f1.createNewFile();
        }

        FileWriter fileWritter = new FileWriter(f1.getName(), true);
        BufferedWriter bw = new BufferedWriter(fileWritter);
        bw.write(mapperService.tournamentObjectMapper().writeValueAsString(matchList));
        bw.close();

    }

    public void saveTeamsToFile(String fileName, List<Team> teamList) throws IOException {
        File f1 = new File("D:\\InventorSovtTasks\\TournaventWithSpring\\myObjects.txt");
        if (!f1.exists()) {
            f1.createNewFile();
        }
        FileWriter fileWritter = new FileWriter(f1.getName(), true);
        BufferedWriter bw = new BufferedWriter(fileWritter);
        bw.write(mapperService.tournamentObjectMapper().writeValueAsString(teamList));
        bw.close();

    }

    public void saveTeamToFile(String fileName, Team team) throws IOException {
        File f1 = new File("D:\\InventorSovtTasks\\TournaventWithSpring\\myObjects.txt");
        if (!f1.exists()) {
            f1.createNewFile();
        }

        FileWriter fileWritter = new FileWriter(f1.getName(), true);
        BufferedWriter bw = new BufferedWriter(fileWritter);
        bw.write(mapperService.tournamentObjectMapper().writeValueAsString(team));
        bw.close();

    }
}
