package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Match {
    private String team1;
    private String team2;
    private String result;
}
