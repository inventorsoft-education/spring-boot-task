package com.example.task5.repository;

import com.example.task5.model.Game;
import com.example.task5.model.Team;
import com.example.task5.service.CSV;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DataStore {

    default ArrayList<Team> getData(ArrayList<Team> teams) {

        return  new CSV().read(teams, "file1.csv");

    }

    default HashMap<String, List<Game>> getData(HashMap<String, List<Game>> games){
        return new CSV().read(games, "file2.csv");
    }

}
