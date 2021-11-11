package com.example.demo.tournament;

import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.repository.BaseMatchRepository;
import com.example.demo.repository.BaseTeamRepository;
import com.example.demo.repository.TeamRepository;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Component
@Value
@AllArgsConstructor
public class Tournament {
    BaseTeamRepository teams;
    BaseMatchRepository matches;

    public void createMatchesBasedOnListOfTeams() {
        List<Team> teamList = teams.getList();
        if (teams.checkValidCntOfTeams()) {
            Random random = new Random();
            int firstRandomNumber;
            int secondRandomNumber;
            int countMatchesAtTheRound = teamList.size() / 2;
            int sizeOfIndexArray = teamList.size();
            int cntOfMatches = teamList.size() - 1;
            int currentRound;
            int cntOfRounds;
            List<Integer> indexes = generateIndexList(sizeOfIndexArray);

            cntOfRounds = returnCntOfRounds(cntOfMatches);

            for (int i = 1; i <= cntOfRounds; i++) {
                currentRound = i;
                for (int j = 0; j < countMatchesAtTheRound; j++) {
                    firstRandomNumber = random.nextInt(sizeOfIndexArray);
                    while (!indexes.contains(firstRandomNumber)) {
                        firstRandomNumber = random.nextInt(sizeOfIndexArray);
                    }
                    indexes.remove((Object) firstRandomNumber);
                    secondRandomNumber = random.nextInt(sizeOfIndexArray);
                    while (!indexes.contains(secondRandomNumber)) {
                        secondRandomNumber = random.nextInt(sizeOfIndexArray);
                    }
                    indexes.remove((Object) secondRandomNumber);
                    matches.getList().add(new Match(teamList.get(firstRandomNumber), teamList.get(secondRandomNumber), currentRound, cntOfRounds, Match.generateScore()));
                }
                sizeOfIndexArray = matches.getList().size();
                indexes = generateIndexList(sizeOfIndexArray);
                countMatchesAtTheRound /= 2;
            }
        }

    }

    private Integer returnCntOfRounds(int cntOfMatches) {
        Integer tmp = cntOfMatches + 1;
        Integer i = 0;
        while (tmp > 1) {
            tmp = tmp / 2;
            i++;
        }
        return i;
    }

    private List<Integer> generateIndexList(int indexListSize) {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < indexListSize; i++) {
            arr.add(i);
        }
        return arr;
    }

    public List<Team> getWinner() {
        try {
            return new ArrayList<Team>(List.of(matches.getList().get(matches.getList().size() - 1).getWinner()));
        } catch (UnexpectedException e) {
            System.out.println("No winner, draw");
            return new ArrayList<Team>(List.of(
                    matches.getList().get(matches.getList().size() - 1).getFirstTeam(),
                    matches.getList().get(matches.getList().size() - 1).getSecondTeam()
            ));
        }
    }

    public void exportWinner() {
        String path = "src/main/resources/winner.csv";

        try (Writer writer = new FileWriter(path)) {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .build();
            sbc.write(getWinner());
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
