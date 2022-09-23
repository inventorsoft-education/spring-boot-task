package com.tetiana.tournament;

import com.tetiana.tournament.controller.AppController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TournamentApplication implements CommandLineRunner {

  private final AppController tournament;

  @Autowired
  public TournamentApplication(AppController tournament) {
    this.tournament = tournament;
  }

  @Override
  public void run(String... args) {
    tournament.chooseTournamentActions();
  }

  public static void main(String[] args) {
    SpringApplication.run(TournamentApplication.class, args);
  }
}
