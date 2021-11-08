package com.example.demo.tournament;

import com.example.demo.model.Team;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.List;

@Controller
public class CSVcontroller {

    public static void Write(List<Team> listTeams) {
        String path = "src/main/resources/Students.csv";

        try (Writer writer = new FileWriter(path)) {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .build();
            sbc.write(listTeams);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }


    }

    public static void Read(List<Team> listTeams) {
        String path = "src/main/resources/Students.csv";

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


}
