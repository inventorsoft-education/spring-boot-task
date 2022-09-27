package Application.Managers;

import Application.Pair;
import Application.Team;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is used to manage things which related to Entities
 * such as
 * adding a team into game
 * creating pairs
 * determine the round winner
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamManager {
    /**
     * flag
     * true if game is still in process
     * false if game ended
     */
    boolean inGame = true;
    /**
     * set to contain teams
     * used set instead of list because in set the iteration order of set elements
     * is unspecified and is subject to change.
     */
    Set<Team> teams;
    /**
     * list to contain pairs
     */
    final List<Pair> pairs = new ArrayList<>();

    TeamManager() {
        teams = new HashSet<>();
    }

    public boolean isInGame() {
        return inGame;
    }

    /**
     * this method requiring from user String team name, String coach name and String capitan name
     *
     * @return true if team was added and false if team was not added
     */
    public boolean addTeam() {
        String cap;
        String name;
        String coach;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter team name!");
        name = scanner.nextLine();
        System.out.println("Enter coach name!");
        coach = scanner.nextLine();
        System.out.println("Enter cap name!");
        cap = scanner.nextLine();
        return teams.add(new Team(cap, coach, name));
    }

    /**
     * This method is iterating through set of teams and creating list of pairs
     */
    public void getPairs() {
        int pairCounter = 1;
        Team[] arr = teams.toArray(new Team[0]);
        pairs.clear();
        for (int i = 0; i < arr.length; i += 2) {
            pairs.add(new Pair(arr[i], arr[i + 1], pairCounter++));
        }
        System.out.println("\nWe have " + teams.size() / 2 + " pairs!");
        System.out.println("List of Pairs below:");
        pairs.stream().map(Pair::toString).forEach(System.out::println);
    }

    /**
     * this method imitates fight
     * and determines the round winner
     */
    public void fight() {
        System.out.println("\nThis is stage 1/" + pairs.size() * 2);
        FileWriterManager.writeSentance("\nThis is stage 1/" + pairs.size() * 2);
        teams.forEach(team -> team.setWinner(false));
        pairs.forEach(Pair::fight);
    }

    /**
     * this method deletes teams which lost
     * and checks if only one team left
     * if it is true
     * than it ends the game
     */
    public void clearPairs() {
        teams = teams.stream().filter(Team::isWinner).collect(Collectors.toSet());
        if (teams.size() == 1) {
            inGame = false;
            System.out.println("\nOUR CHAMPION IS " + pairs.get(0).getResult().getName() + "!!!!!");
            FileWriterManager.writeSentance("\nOUR CHAMPION IS " + pairs.get(0).getResult().getName() + "!!!!!");
        } else {
            getPairs();
        }
    }


}
