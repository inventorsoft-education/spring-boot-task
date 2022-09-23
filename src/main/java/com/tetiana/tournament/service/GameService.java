package com.tetiana.tournament.service;

import com.tetiana.tournament.domain.Game;
import com.tetiana.tournament.domain.Team;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class GameService {
  public List<Game> generateGameList(List<Team> teams) {
    Collections.shuffle(teams);
    Team firstTeam, secondTeam;
    String level;
    List<Game> games = new ArrayList<>();
    for (int i = 0; i < teams.size(); i++) {
      firstTeam = teams.get(i);
      secondTeam = teams.get(++i);
      level = "1/" + teams.size() / 2;
      Game game = new Game(firstTeam, secondTeam, level, writeResult(firstTeam, secondTeam));
      games.add(game);
    }
    return games;
  }

  private String writeResult(Team t1, Team t2) {
    StringBuilder builder = new StringBuilder();
    builder.append(t1.getName())
        .append(":")
        .append(t2.getName())
        .append(" --->> ")
        .append(t1.getPoint())
        .append(":")
        .append(t2.getPoint());
    return builder.toString();
  }

  public List<Team> findWinners(List<Game> games) {
    List<Team> teams = new ArrayList<>();
    for (Game game : games) {
      Team team = chooseTeam(game.getFirstTeam(), game.getSecondTeam());
      teams.add(team);
    }
    return teams;
  }

  private Team chooseTeam(Team firstTeam, Team secondTeam) {
    if (firstTeam.getPoint() > secondTeam.getPoint()) {
      return firstTeam;
    } else if (firstTeam.getPoint() < secondTeam.getPoint()) {
      return secondTeam;
    }
    else {
      if ((int) (Math.random() * 2 + 1) == 2) {
        return firstTeam;
      }
    }
    return secondTeam;
  }

}
