package com.example.springboot;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamList {

    List<Team> teamList;
    FileReadWrite fileReadWrite;

    @Autowired
    TeamList(FileReadWrite fileReadWrite) {
        teamList = new ArrayList<>();
        this.fileReadWrite = fileReadWrite;
        read();
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void read() {
        fileReadWrite.readTeam(teamList);
    }

    public void write() {
        fileReadWrite.writeTeam(teamList);
    }

    public void addTeam(Team team) {
        teamList.add(team);
        write();
    }

//    public void deleteTeam(Team team) {
//        teamList.remove(team);
//    }

    public int getSize() {
        return teamList.size();
    }

}
