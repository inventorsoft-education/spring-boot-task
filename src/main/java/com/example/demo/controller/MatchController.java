package com.example.demo.controller;

import com.example.demo.models.Match;
import com.example.demo.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Match>> tossTeams(){
        return new ResponseEntity<>(matchService.tossTeams(), HttpStatus.OK);
    }
    @PostMapping("/play")
    public ResponseEntity<Match> playMatch(){
        return new ResponseEntity<>(matchService.playMatch(),HttpStatus.OK);

    }


}
