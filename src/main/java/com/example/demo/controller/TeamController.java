package com.example.demo.controller;

import com.example.demo.models.Team;
import com.example.demo.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;

     @PostMapping
     public ResponseEntity<Team> addTeam(@RequestBody Team team){
         teamService.createTeam(team);
         return new ResponseEntity<>(team, HttpStatus.CREATED);
     }

    @DeleteMapping
    public void  deleteTeam(@RequestBody String teamName){
        teamService.deleteTeam(teamName);
    }




}
