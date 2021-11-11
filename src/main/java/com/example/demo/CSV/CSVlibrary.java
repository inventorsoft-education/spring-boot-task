package com.example.demo.CSV;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class CSVlibrary {

    public void writeTeam(List<Team> listTeams) {
        String path = "src/main/resources/teams.csv";

        try (Writer writer = new FileWriter(path)) {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .build();
            sbc.write(listTeams);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }

    public void writeMatches(List<Match> listMatches) {
        String path = "src/main/resources/matches.csv";

        try (Writer writer = new FileWriter(path)) {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .build();
            sbc.write(listMatches);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }

    public void readTeam(List<Team> listTeams) {
        String path = "src/main/resources/teams.csv";

        try (FileReader filereader = new FileReader(path)) {
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> strings = csvReader.readAll();
            for (String[] arr : strings) {
                listTeams.add(new Team(arr[2], arr[0], arr[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readMatches(List<Match> listMatches) {
        String path = "src/main/resources/matches.csv";

        try (FileReader filereader = new FileReader(path)) {
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> strings = csvReader.readAll();
            for (String[] arr : strings) {
                //listMatches.add(new Match());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
