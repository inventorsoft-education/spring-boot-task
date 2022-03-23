package com.DAO;

import com.entity.Team;
import com.entity.Tournament;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TournamentDAO {
    private final String directoryName="tournaments results";
    public void save(Tournament tournament){
        String name=new SimpleDateFormat("dd-MM-HH-mm").format(System.currentTimeMillis());
        File data=new File(directoryName+"/"+name+".csv");
        try {
            data.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(directoryName+"/"+name+".csv"));
            for(String[] record:tournament.getPlayedMatches())
                writer.writeNext(record);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getFileNames(){
        File dir = new File(directoryName);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        String result="";
        for(File current:lst){
            result+=current.getName()+"\n";
        }
        return result;
    }
    public String getResults(String name){
        CSVReader reader=null;
        List<String[]> strData = null;
        String result="";
        try{
            reader = new CSVReader(new FileReader(directoryName+"/"+name+".csv"));}
        catch (FileNotFoundException e) {
           return "No tournament found on this date.";
        }
        try {
            strData = reader.readAll();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        for(String[] match:strData){
            result+=match[0]+", "+match[1]+", "+match[2]+", "+match[3];
        }
        return result;
    }
    public void removeResult(String name){
        File file=new File(directoryName+"/"+name+".csv");
        file.delete();
    }
    @PostConstruct
    private void init(){
        if(!Files.exists(Paths.get(directoryName))){
            try {
                Files.createDirectory(Paths.get(directoryName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
