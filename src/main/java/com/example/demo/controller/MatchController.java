package com.example.demo.controller;

import com.example.demo.models.Match;
import com.example.demo.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/match")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/toss")
    public List<Match> tossTeams(){
      return matchService.tossTeams();
    }
    @PostMapping("/play")
    public Match playMatch(){
        return  matchService.playMatch();
    }



}
