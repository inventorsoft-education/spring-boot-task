package com.example.springboot;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchList {

    List<Match> matchList;
    FileReadWrite fileReadWrite;

    @Autowired
    MatchList(FileReadWrite fileReadWrite) {
        matchList = new ArrayList<>();
        this.fileReadWrite = fileReadWrite;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void read() {
        fileReadWrite.readMatch(matchList);
    }

    public void write() {
        fileReadWrite.writeMatch(matchList);
    }

}
