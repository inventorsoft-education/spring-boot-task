package com.tetiana.tournament.exception;


import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class InvalidAmountOfTeamException extends RuntimeException{

  public InvalidAmountOfTeamException(int number) {
    super("The number has to be 2^n, where n > 0. Bad input: " + number );
  }
}
