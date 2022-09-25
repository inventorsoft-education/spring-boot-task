package com.tetiana.tournament.utils;

import java.util.Random;

public class Utils {
  private static final Random RANDOM = new Random();

  public static int getRandom(int bound){
    return RANDOM.nextInt(bound);
  }
}
