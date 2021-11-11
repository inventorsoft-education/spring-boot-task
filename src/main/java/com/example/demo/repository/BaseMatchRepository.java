package com.example.demo.repository;

import com.example.demo.model.Match;
import com.example.demo.model.Team;

import java.util.List;

public interface BaseMatchRepository {
    List<Match> getList();

    void readFromFile();

    void writeToFile(List<Match> matches);
}
