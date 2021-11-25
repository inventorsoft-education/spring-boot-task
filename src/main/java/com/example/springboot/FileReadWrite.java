package com.example.springboot;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class FileReadWrite {
    String teamPath = "src\\main\\resources\\teams.csv";
    String matchPath = "src\\main\\resources\\matches.csv";

    public void writeTeam(List<Team> teamList) {
        try (Writer writer = new FileWriter(teamPath)) {
            StatefulBeanToCsv statefulBeanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            statefulBeanToCsv.write(teamList);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }

    public void writeMatch(List<Match> matchList) {
        try (Writer writer = new FileWriter(matchPath)) {
            StatefulBeanToCsv statefulBeanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            statefulBeanToCsv.write(matchList);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }

    public void readTeam(List<Team> teamList) {
        try (FileReader fileReader = new FileReader(teamPath)) {
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .build();
            List<String[]> strings = csvReader.readAll();
            for (String[] arr : strings) {
                teamList.add(new Team(arr[2], arr[0], arr[1]));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public List<Match> readMatch(List<Match> matchList) {
        try (FileReader fileReader = new FileReader(matchPath)) {
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .build();
            List<String[]> strings = csvReader.readAll();
            for (String[] arr : strings) {
                matchList.add(new Match());
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return matchList;
    }

}
