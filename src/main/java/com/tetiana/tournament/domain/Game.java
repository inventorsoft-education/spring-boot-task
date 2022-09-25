package com.tetiana.tournament.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
public class Game {
  Team firstTeam;
  Team secondTeam;
  String level;
  String result;

  @Override
  public String toString() {
    return "LEVEL:  " + level + " RESULT: " + result;
  }
}
