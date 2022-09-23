package com.tetiana.tournament.view;

import com.tetiana.tournament.domain.Game;
import com.tetiana.tournament.domain.Team;
import com.tetiana.tournament.repository.TeamRepository;
import com.tetiana.tournament.controller.dto.TeamRequest;
import com.tetiana.tournament.domain.Action;
import com.tetiana.tournament.exception.InvalidAmountOfTeamException;
import com.tetiana.tournament.exception.InvalidIndexException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class View {
  public static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
  public static final List<Integer> NUMBERS = List.of(2, 4, 8, 16, 32, 64);

  public void showGames(List<Game> games) {
    System.out.println("<<<<<LEVEL " + games.stream().findFirst().get().getLevel() + ">>>>>");
    games.forEach(System.out::println);
    System.out.println();
  }

  public String inputString() {
    try {
      return READER.readLine();
    }
    catch (IOException e) {
      return inputString();
    }
  }

  public int inputTeamSize() {
    System.out.println("Input amount of teams, something like 2, 4, 8, 16, 32, 64");
    int number;
    try {
      number = Integer.parseInt(READER.readLine());
      if (number > TeamRepository.TEAMS.size() || !NUMBERS.contains(number)) {
        throw new InvalidAmountOfTeamException(number);
      }
    }
    catch (InvalidAmountOfTeamException | IOException e) {
      System.out.println("Invalid input. Try again.");
      return inputTeamSize();
    }
    return number;
  }

  public Action chooseAction() {
    Arrays.stream(Action.values()).forEach(action -> System.out.println(action + action.getLabel()));
    System.out.println("Input action: ");
    Action action;
    try {
      action = Action.valueOf(READER.readLine().toUpperCase(Locale.ROOT));
    }
    catch (RuntimeException | IOException e) {
      System.out.println("Invalid input. Try again.");
      return chooseAction();
    }
    return action;
  }

  public TeamRequest createTeamRequest() {
    try {
      System.out.println("Input team name: ");
      String name = READER.readLine();
      System.out.println("Input capitan name: ");
      String capitan = READER.readLine();
      System.out.println("Input coach name: ");
      String coach = READER.readLine();
      return new TeamRequest(name, capitan, coach);
    }
    catch (IOException e) {
      return createTeamRequest();
    }
  }

  public List<Team> chooseTeams(int teamSize) {
    printAlTeams(TeamRepository.TEAMS);
    List<Team> currentTeams = new ArrayList<>();
    int index;
    while (currentTeams.size() != teamSize) {
      index = inputIndex();
      Team team = TeamRepository.TEAMS.get(index);
      if (currentTeams.contains(team)) {
        continue;
      }
      currentTeams.add(team);
      System.out.println("Team with index " + index + " was added");
    }
    return currentTeams;
  }

  public void printAlTeams(List<Team> teams) {
    int index = 0;
    for (Team team : teams) {
      System.out.println("index: " + index++ + "; team :" + team);
    }
    System.out.println();
  }

  public void showTeams(List<Team> teams) {
    System.out.println("<<<<<<<TEAMS>>>>>>>");
    teams.forEach(System.out::println);
    System.out.println();
  }

  public int inputIndex() {
    System.out.println("Choose team index: ");
    int index;
    try {
      index = Integer.parseInt(READER.readLine());
      indexValidator(index);
    }
    catch (InvalidIndexException | IOException ex) {
      System.out.println("Bed input. Try again");
      return inputIndex();
    }
    return index;
  }

  public void showResult(Team team) {
    System.out.println(team);
  }

  private void indexValidator(int index) {
    if (index >= TeamRepository.TEAMS.size() || index < 0) {
      throw new InvalidIndexException();
    }
  }
}
