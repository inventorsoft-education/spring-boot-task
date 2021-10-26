package com.example.task5.service;

import com.example.task5.model.Game;
import com.example.task5.model.Team;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@Component
public class CSV {

    public static void write(ArrayList<Team> list, String pathTeam){
        try (Writer writer = new FileWriter(pathTeam)) {
            StatefulBeanToCsv<Team> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Team>(writer).build();
            statefulBeanToCsv.write(list);
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data was exported to " + pathTeam);
    }

    public static void write(Map<String, List<Game>> map, String pathGame){
        ArrayList<Game> ttemp = new ArrayList<>();
        for (String s : map.keySet()) {
            ttemp.addAll(map.get(s));
        }
        try (Writer writer = new FileWriter(pathGame)) {
            StatefulBeanToCsv<Game> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Game>(writer).build();
            statefulBeanToCsv.write(ttemp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
        System.out.println("Data was expoeted to " + pathGame);
    }

    public static ArrayList<Team> read(ArrayList<Team> list, String pathTeam){
        System.out.println("Reading data from " + pathTeam);
        try {
            FileReader filereader = new FileReader(pathTeam);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> strings = csvReader.readAll();
            for (String[] arr :strings ) {
                list.add(new Team(arr[0], arr[1], arr[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static HashMap<String, List<Game>> read(HashMap<String, List<Game>> map, String pathTeam){
        System.out.println("Reading data from " + pathTeam);
        try {
            FileReader filereader = new FileReader(pathTeam);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> strings = csvReader.readAll();
            for (String[] arr :strings ) {
                String[] temparr = arr[2].split(", ");
                String[] temparr2 = arr[3].split(", ");
                map.put(arr[1], new ArrayList<>());
                map.get(arr[1]).add(new Game(
                        new Team(temparr[0], temparr[1], temparr[2]),
                        new Team(temparr2[0], temparr2[1], temparr2[2]),
                        arr[1], arr[0]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
