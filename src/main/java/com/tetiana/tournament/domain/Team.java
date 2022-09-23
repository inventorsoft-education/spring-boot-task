package com.tetiana.tournament.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;


@ToString
@Getter
@Setter
@Builder
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
    point = (int) (Math.random() * 8);
  }
}
