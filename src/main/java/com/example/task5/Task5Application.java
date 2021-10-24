package com.example.task5;

import com.example.task5.service.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task5Application implements CommandLineRunner {

	private Tournament tournament;

	@Autowired
	public Task5Application(Tournament tournament) {
		this.tournament = tournament;
	}

	@Override
	public void run(String... args) throws Exception {

		tournament.introduce();
		tournament.register();
		tournament.printTeamsTable();
		tournament.start();
		tournament.printGamesTable();
		System.out.println("Winner " + tournament.getWinner());
		tournament.resultsExport();

	}

	public static void main(String[] args) {
		SpringApplication.run(Task5Application.class, args);
	}
}
