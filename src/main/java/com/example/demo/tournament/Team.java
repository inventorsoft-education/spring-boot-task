package com.example.demo.tournament;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
public class Team {

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private String captain;

    @CsvBindByPosition(position = 3)
    private String coach;

    public Team(String name, String captain, String coach) {
        this.name = name;
        this.captain = captain;
        this.coach = coach;
    }

    public Team(List<String> teamData){
        this.name = teamData.get(0);
        this.captain = teamData.get(1);
        this.coach = teamData.get(2);
    }

}
