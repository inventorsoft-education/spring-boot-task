package com.example.task5.configuration;

import com.example.task5.model.Game;
import com.example.task5.model.Team;
import com.example.task5.repository.DataStore;
import com.example.task5.repository.ListOfGames;
import com.example.task5.repository.ListOfTeams;
import com.example.task5.service.CSV;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class Config {

    @Bean
    public ListOfTeams listOfTeams() {
        return new ListOfTeams(dataStore());
    }

    @Bean
    public ListOfGames listOfGames(){
        return new ListOfGames(dataStore());}

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public DataStore dataStore() {
        return new DataStore() {
            @Value("${pathToTeamsList}")
            String pathTeam;
            @Value("${pathToGameList}")
            String pathGame;

            @Override
            public ArrayList<Team> getData(ArrayList<Team> teams) {
                System.out.println("тут буде" + pathTeam);
                return  CSV.read(teams, pathTeam);
            }

            @Override
            public HashMap<String, List<Game>> getData(HashMap<String, List<Game>> games) {
                System.out.println("тут буде " + pathGame);
                return CSV.read(games, pathGame);
            }

            @Override
            public void setData(ArrayList<Team> list) {
                CSV.write(list, pathTeam);
            }

            @Override
            public void setData(Map<String, List<Game>> map) {
                CSV.write(map, pathGame);
            }
        };}

}
