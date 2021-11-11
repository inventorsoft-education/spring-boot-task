package com.example.demo;

import com.example.demo.console.ConsoleInOut;
import com.example.demo.model.Match;
import com.example.demo.tournament.Tournament;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@AllArgsConstructor

public class DemoApplication implements CommandLineRunner {

    private final ConsoleInOut console;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        console.getTournament().createMatchesBasedOnListOfTeams();
        console.outMatchList();
        console.outWinner();
    }
}
