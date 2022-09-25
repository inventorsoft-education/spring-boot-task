package com.example.demo.controller;

import com.example.demo.models.Team;
import com.example.demo.service.TeamService;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @DeleteMapping("/{teamName}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable String teamName){
        teamService.deleteTeam(teamName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Map<String,Team>>  findAll(){
        return new ResponseEntity<>(teamService.findAllTeams(),HttpStatus.OK);
    }
}
