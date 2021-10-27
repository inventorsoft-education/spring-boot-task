package com.example.demo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

//@Getter

@Data
public class Team {

    private String teamName;
    private String capitan;
    private String coach;

//@Autowired
//    public Team(String teamName, String capitan, String coach) {
//        this.teamName = teamName;
//        this.capitan = capitan;
//        this.coach = coach;
    }

