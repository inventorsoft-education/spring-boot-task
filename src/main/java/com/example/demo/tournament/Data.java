package com.example.demo.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.PrintWriter;

@Component
public class Data {

    private String filePath = "data.csv";
    private String writePath = "write_file.csv";
    private PrintWriter writer;

    @Autowired
    public Data(PrintWriter writer){
        this.writer = writer;
    }

    public List<Team> readData() throws IOException {
        return parseTeamsData();
    }

    public void closeFile(){
        writer.close();
    }

    private List<Team> parseTeamsData() throws IOException {
        List<Team> teams = new ArrayList<>();
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        for(List<String> e: records){
            Team team = new Team(e);
            teams.add(team);
        }

        return teams;
    }

    public void writeDataToFile(String matchInfo) {
        StringBuilder info = new StringBuilder();
        info.append(matchInfo);

        writer.write(info.toString());
        writer.write("\n");

    }

}
