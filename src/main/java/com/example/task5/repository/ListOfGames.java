package com.example.task5.repository;

import com.example.task5.model.Game;
import com.example.task5.model.Team;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListOfGames {
    private Map<String, List<Game>> games;
    private String path;

    public ListOfGames(String path) {
        games = new HashMap<>();
        this.path = path;
    }

    public String getPath() { return path; }
    public Map<String, List<Game>> getGames(){
        return games;
    }

    public void addGame(Game game) {
        if (!games.containsKey(game.getRound()))
            games.put(game.getRound(), new ArrayList<>());
        games.get(game.getRound()).add(game);
    }

    public void printGame() {
        System.out.println("**********************");
        System.out.println("GAMES LIST");
        for (String s : games.keySet()) {
            games.get(s).stream().forEach(System.out::println);
        }
        System.out.println("**********************");
    }

    public List<Team> getWinners(String round) {
        if (!games.containsKey(round)) {
            return new ArrayList<>();
        }
        return games.get(round).stream().map(Game::getWinner).collect(Collectors.toList());
    }

    public void toCVS() {
        ArrayList<Game> ttemp = new ArrayList<>();
        for (String s : games.keySet()) {
            ttemp.addAll(games.get(s));
        }
            try (Writer writer = new FileWriter(path)) {
                StatefulBeanToCsv<Game> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Game>(writer).build();
                statefulBeanToCsv.write(ttemp);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvRequiredFieldEmptyException e) {
                e.printStackTrace();
            } catch (CsvDataTypeMismatchException e) {
                e.printStackTrace();
            }
        }
}
