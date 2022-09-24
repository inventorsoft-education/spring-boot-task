package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Team {
    private String name;
    private String capitan;
    private String coach;
    private int wins;


}
