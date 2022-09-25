package com.tetiana.tournament.repository;

import com.tetiana.tournament.domain.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamRepository {
  public static final List<Team> TEAMS = new ArrayList<>();

  static {
    TEAMS.add(new Team("name1", "capitan1", "coach1"));
    TEAMS.add(new Team("name2", "capitan2", "coach2"));
    TEAMS.add(new Team("name3", "capitan3", "coach3"));
    TEAMS.add(new Team("name4", "capitan4", "coach4"));
    TEAMS.add(new Team("name5", "capitan5", "coach5"));
    TEAMS.add(new Team("name6", "capitan6", "coach6"));
    TEAMS.add(new Team("name7", "capitan7", "coach7"));
    TEAMS.add(new Team("name8", "capitan8", "coach8"));
    TEAMS.add(new Team("name9", "capitan9", "coach9"));
    TEAMS.add(new Team("name10", "capitan10", "coach10"));
    TEAMS.add(new Team("name11", "capitan11", "coach11"));
    TEAMS.add(new Team("name12", "capitan12", "coach12"));
  }

  public void save(Team team) {
    TEAMS.add(team);
  }

}
