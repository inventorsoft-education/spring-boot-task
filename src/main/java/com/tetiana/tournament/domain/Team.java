package com.tetiana.tournament.domain;

import com.tetiana.tournament.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@ToString
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
public class Team {
  String name;
  String capitan;
  String coach;
  @ToString.Exclude
  int point;

  public Team(String name, String capitan, String coach) {
    this.name = name;
    this.capitan = capitan;
    this.coach = coach;
  }

  public void playGame() {
    point = Utils.getRandom(8);
  }
}
