package com.tetiana.tournament.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Action {

  CREATE(" - Create team"),
  CHOOSE(" - Choose teams for tournament"),
  GENERATE(" - Choose teams the random way");

  private final String label;
}
