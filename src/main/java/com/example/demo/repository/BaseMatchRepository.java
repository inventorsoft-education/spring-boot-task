package com.example.demo.repository;

import com.example.demo.model.Match;

import java.util.List;

public interface BaseMatchRepository {
    List<Match> getList();

    void readFromFile();

    void writeToFile(List<Match> matches);
}
