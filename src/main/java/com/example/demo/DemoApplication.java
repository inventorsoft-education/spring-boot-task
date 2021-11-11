package com.example.demo;

import com.example.demo.console.ConsoleInOut;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
