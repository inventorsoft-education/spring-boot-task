package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private String name;
    private String capitan;
    private String coach;

    public int roll(){
        return (int) (1+Math.random()*100);
    }
}
