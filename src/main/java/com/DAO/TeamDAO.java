package com.DAO;

import com.entity.Team;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class TeamDAO {
private final String fileName="TeamsDB.csv";

@PostConstruct
private void init(){
    File data=new File(fileName);
    try {
        data.createNewFile();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public List<Team> getData(){
    CSVReader reader=null;
    List<String[]> strData = null;
    List<Team> result = new ArrayList<>();
    try{
    reader = new CSVReader(new FileReader(fileName));}
    catch (FileNotFoundException e) {
        e.printStackTrace();
        return null;
    }
    try {
        strData = reader.readAll();
    }catch (IOException e) {
        e.printStackTrace();
    } catch (CsvException e) {
        e.printStackTrace();
    }
    for(String[] team:strData){
        result.add(new Team(team[0],team[1],team[2]));
    }
    return result;
}
    public boolean has(Team participant){
    List<Team> data=this.getData();
    for(Team team:data){
        if(team.getTeamName().toLowerCase(Locale.ROOT).equals(participant.getTeamName().toLowerCase(Locale.ROOT)))
            return true;
    }
    return false;
    }
    public boolean add(Team team){
        if(team==null) return false;
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(fileName));
            String [] record ={team.getTeamName(),team.getCaptain(),team.getCoach()} ;
            writer.writeNext(record);
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
