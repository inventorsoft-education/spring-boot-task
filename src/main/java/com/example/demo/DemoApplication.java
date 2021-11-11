package com.example.demo;

import com.example.demo.tournament.ConsoleWriter;
import com.example.demo.tournament.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Tournament tournament;

    @Autowired
    public DemoApplication(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public void run(String... args) throws Exception{
        tournament.nextRound();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
