package com.example.tournament;

import com.example.tournament.Service.TournamentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class TournamentApplication {

    @Autowired
    private TournamentService tournamentService;

    
    public static void main(String[] args) throws IOException {
        SpringApplication.run(TournamentApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        tournamentService.runProgram();
    }

}
