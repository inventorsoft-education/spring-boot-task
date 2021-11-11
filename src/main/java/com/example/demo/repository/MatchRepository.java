package com.example.demo.repository;

import com.example.demo.CSV.CSVlibrary;
import com.example.demo.model.Match;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MatchRepository implements BaseMatchRepository {
    List<Match> matches;
    CSVlibrary csv;

    MatchRepository(CSVlibrary csv) {
        this.csv = csv;
        matches = new ArrayList<>();
    }

    @Override
    public List<Match> getList() {
        return matches;
    }

    @Override
    public void readFromFile() {
        csv.readMatches(matches);
    }

    @Override
    public void writeToFile(List<Match> matches) {
        csv.writeMatches(matches);
    }
}
