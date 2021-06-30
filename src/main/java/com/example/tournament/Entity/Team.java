package com.example.tournament.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private String teamName;
    private String coachOfTeam;
    private String captainOfTeam;

}
