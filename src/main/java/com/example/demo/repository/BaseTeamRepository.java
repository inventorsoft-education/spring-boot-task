package com.example.demo.repository;

import com.example.demo.model.Team;

import java.util.List;

public interface BaseTeamRepository {
    List<Team> getList();

    boolean add(final Team team);

    boolean remove(final Team team);

    void readFromFile();

    void writeToFile();

    void writeToFile(List<Team> teams);

    boolean checkValidCntOfTeams();


}
