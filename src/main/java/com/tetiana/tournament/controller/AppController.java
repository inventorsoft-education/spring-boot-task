package com.tetiana.tournament.controller;

import com.tetiana.tournament.controller.dto.TeamRequest;
import com.tetiana.tournament.domain.Action;
import com.tetiana.tournament.domain.Game;
import com.tetiana.tournament.domain.Team;
import com.tetiana.tournament.domain.Tournament;
import com.tetiana.tournament.service.GameService;
import com.tetiana.tournament.service.TeamService;
import com.tetiana.tournament.view.CsvWriter;
import com.tetiana.tournament.view.View;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AppController {
  public static final String PATH = "src/main/java/com/tetiana/tournament/files/f.csv";
  TeamService teamService;
  GameService gameService;
  View view;

  public void chooseTournamentActions() {
    System.out.println("Press any keyword for start");
    while (!(view.inputString().equalsIgnoreCase("exit"))) {
      Tournament tournament;
      Action action = view.chooseAction();
      switch (action) {
        case CREATE -> createTeam();
        case CHOOSE -> {
          tournament = createTournament();
          view.showResult(findWinner(tournament));
        }
        case GENERATE -> {
          tournament = generateTournament();
          findWinner(tournament);
          view.showResult(tournament.getWinner());
        }
      }
      System.out.println("For finish input - [exit] or anything for continue");
    }
  }

  public Tournament generateTournament() {
    int amountOfTeam = view.inputTeamSize();
    List<Team> teams = teamService.findRandomTeams(amountOfTeam);
    List<Game> games = gameService.generateGameList(teams);
    return new Tournament(teams, games, amountOfTeam / 2);
  }

  public Tournament createTournament() {
    int amountOfTeam = view.inputTeamSize();
    List<Team> teams = view.chooseTeams(amountOfTeam);
    List<Game> games = gameService.generateGameList(teams);
    return new Tournament(teams, games, teams.size() / 2);
  }

  public Team findWinner(Tournament tournament) {
    List<Team> teams = tournament.getTeams();
    view.showTeams(teams);
    teams.forEach(Team::playGame);
    List<Game> games = gameService.generateGameList(teams);
    view.showGames(games);
    tournament.setGames(games);
    if (games.size() > 1) {
      teams = gameService.findWinners(games);
      tournament.setTeams(teams);
      return findWinner(tournament);
    }
    CsvWriter.write(teams, PATH);
    Team team = gameService.findWinners(games).get(0);
    tournament.setWinner(team);
    return team;
  }

  public void createTeam() {
    TeamRequest request = view.createTeamRequest();
    teamService.create(request);
  }
}