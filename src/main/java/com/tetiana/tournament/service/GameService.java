package com.tetiana.tournament.service;

import com.tetiana.tournament.domain.Game;
import com.tetiana.tournament.domain.Team;
import com.tetiana.tournament.utils.Utils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@NoArgsConstructor
public class GameService {
  public List<Game> generateGameList(List<Team> teams) {
    Collections.shuffle(teams);
    return IntStream.range(0, teams.size()/2)
        .mapToObj(i -> generateGame(teams, i))
        .toList();
  }

  private Game generateGame(List<Team> teams, int i) {
    String level = "1/" + teams.size() / 2;
    int size = teams.size()/2;
    return new Game(teams.get(i), teams.get(i + size), level, writeResult(teams.get(i), teams.get(i + size)));
  }

  private String writeResult(Team t1, Team t2) {
    return String.format(" %s : %s -----> %d : %d", t1.getName(), t2.getName(), t1.getPoint(), t2.getPoint());
  }

  public List<Team> findWinners(List<Game> games) {
    return games.stream().map(this::chooseTeam).collect(Collectors.toList());
  }

  private Team chooseTeam(Game game) {
    Team firstTeam = game.getFirstTeam();
    Team secondTeam = game.getSecondTeam();
    if (firstTeam.getPoint() > secondTeam.getPoint()) {
      return firstTeam;
    } else if (firstTeam.getPoint() < secondTeam.getPoint()) {
      return secondTeam;
    } else {
      if ((Utils.getRandom(2) + 1 == 2)) {
        return firstTeam;
      }
    }
    return secondTeam;
  }

}
