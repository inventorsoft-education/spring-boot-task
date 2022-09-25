package com.tetiana.tournament.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@ToString
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
public class Tournament {
  List<Team> teams;
  List<Game> games;
  Integer levels;
  Team winner;

  public Tournament(List<Team> teams, List<Game> games, Integer levels) {
    this.teams = teams;
    this.games = games;
    this.levels = levels;
  }
}
