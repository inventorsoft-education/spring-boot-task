package com.example.task5.configuration;

import com.example.task5.repository.ListOfGames;
import com.example.task5.repository.ListOfTeams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ListOfTeams listOfTeams(@Value("${pathToTeamsList}") String path) {return new ListOfTeams(path);
    }

    @Bean
    public ListOfGames listOfGames(@Value("${pathToGameList}") String path){return new ListOfGames(path);}

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

}
