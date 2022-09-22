package com.example.demo.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Team {
    private String name;
    private String capitan;
    private String coach;
    private int wins;

    public Team(String name, String capitan, String coach) {
        this.name = name;
        this.capitan = capitan;
        this.coach = coach;
    }
}
