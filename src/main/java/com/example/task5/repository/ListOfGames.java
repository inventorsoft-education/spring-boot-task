package com.example.task5.repository;
import com.example.task5.model.Game;
import com.example.task5.model.Team;
import java.util.*;
import java.util.stream.Collectors;

public class ListOfGames {
    private HashMap<String, List<Game>> games;
    private DataStore dataStore;

    public ListOfGames(DataStore dataStore) {
        games = new HashMap<>();
        this.dataStore = dataStore;
    }

    public void upData(){
        dataStore.setData(games);
    }

    public void setGames(){
        this.games = dataStore.getData(new HashMap<>());
    }

    public Map<String, List<Game>> getGames(){
        return games;
    }

    public void addGame(Game game) {
        games.compute(game.getRound(), (round, existingGames) -> {
            final List<Game> games = Objects.requireNonNullElseGet(existingGames, ArrayList::new);
            games.add(game);
            return games;
        });
    }



    public List<Team> getWinners(String round) {
        if (!games.containsKey(round)) {
            return new ArrayList<>();
        }
        return games.get(round).stream().map(Game::getWinner).collect(Collectors.toList());
    }
}
