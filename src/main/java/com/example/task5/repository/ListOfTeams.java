package com.example.task5.repository;

import com.example.task5.model.Team;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ListOfTeams {
    List<Team> teams;
    private String path;

    public ListOfTeams(String path){
        teams = new ArrayList<>();
        this.path = path;
    }

    public boolean add(Team team){ return teams.add(team);}

    public ArrayList<Team> get(){
        return new ArrayList<>(teams);
    }

    public int size(){
        return teams.size();
    }

    public void print(){
        System.out.println("**********************");
        System.out.println("TEAMS LIST");
        for (Team t: teams) {
            System.out.println(t);
        }
        System.out.println("**********************");
    }
    public void toCVS() {
        try (Writer writer = new FileWriter(path)) {
            StatefulBeanToCsv<Team> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Team>(writer).build();
            statefulBeanToCsv.write(teams);
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getPath() {
        return path;
    }
}
