package com.example.demo.repository;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Tournament {
    BaseTeamRepository teams;

    Tournament(TeamRepository teamRepository){
        teams = teamRepository;
    }



}
