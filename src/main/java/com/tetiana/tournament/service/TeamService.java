package com.tetiana.tournament.service;

import com.tetiana.tournament.domain.Team;
import com.tetiana.tournament.repository.TeamRepository;
import com.tetiana.tournament.controller.dto.TeamRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TeamService {

  TeamRepository teamRepository;
  public void create(TeamRequest request){
    String name = request.getName();
    String capitan = request.getCapitan();
    String coach = request.getCoach();
    Team team = new Team(name, capitan, coach);
    save(team);
  }

  public List<Team> findRandomTeams(int number){
    List<Team> teams = TeamRepository.TEAMS;
    Collections.shuffle(teams);
    return teams.stream().limit(number).collect(Collectors.toList());
  }

  public void save(Team team){
    teamRepository.save(team);
  }
}
