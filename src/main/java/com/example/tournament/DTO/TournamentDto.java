package com.example.tournament.DTO;

import com.example.tournament.Entity.Match;
import com.example.tournament.Entity.Team;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TournamentDto {

    private List<Team> teams;
    private List<Match> matches;
    private Team team;

}
