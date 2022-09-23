package com.tetiana.tournament.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@Builder
@Getter
@FieldDefaults(level = PRIVATE)
public class TeamRequest {
  String name;
  String capitan;
  String coach;
}
