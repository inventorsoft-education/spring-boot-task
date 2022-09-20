package co.inventorsoft.academy.dao;

import co.inventorsoft.academy.interfaces.ColorText;
import co.inventorsoft.academy.model.Team;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TeamDAO implements ColorText {
    private final List<Team> teams = new ArrayList<>(); //list of teams
    private static final int COUNT_OF_TEAM = 8; // size of tournament

    /**
     * This method add to list 10 teams
     */
    public void createList() {
        teams.add(new Team("Scuderia Ferrari", "Charles Leclerc", "Carlos Sainz Jr."));
        teams.add(new Team("McLaren F1 Team", "Daniel Ricciardo", "Lando Norris"));
        teams.add(new Team("Mercedes-AMG Petronas F1 Team", "Lewis Hamilton", "George Russell"));
        teams.add(new Team("BWT Alpine F1 Team", "Fernando Alonso", "Esteban Ocon"));
        teams.add(new Team("Scuderia AlphaTauri", "Pierre Gasly", "Yuki Tsunoda"));
        teams.add(new Team("Aston Martin F1 Team", "Sebastian Vettel", " Lance Stroll"));
        teams.add(new Team("Alfa Romeo F1 Team Orlen", "Zhou Guanyu", "Valtteri Bottas"));
        teams.add(new Team("Oracle Red Bull Racing", "Max Verstappen", "Sergio Pérez"));
        teams.add(new Team("Williams Racing", "Nicholas Latifi", "Alexander Albon"));
        teams.add(new Team("Haas F1 Team", "Kevin Magnussen", "Mick Schumacher"));
    }

    /**
     * This method add team to list
     *
     * @param team new team
     * @return true if add is correct, else false;
     */
    public boolean add(Team team) {
        return teams.add(team);
    }

    /**
     * This method return size of list
     *
     * @return size of list
     */
    public int size() {
        return teams.size();
    }

    /**
     * This method get team from list by input index
     *
     * @param num input index
     * @return team from list
     */
    public Team getTeam(Integer num) {
        return teams.get(num);
    }

    /**
     * This method delete random team if size of list bigger than COUNT_OF_TEAM
     */
    public void createCorrectPool() {
        Collections.shuffle(teams, new Random()); //random shuffle my list
        teams.subList(COUNT_OF_TEAM, teams.size()).clear();  //delete superfluous teams
        teams.forEach(System.out::println); //display teams on list
    }

    /**
     * This method delete team from list
     *
     * @param team input team
     */
    public void deleteTeam(Team team) {
        teams.remove(team);
    }

    /**
     * This method generate new value of point in next round
     */
    public void generateNewPoints() {
        teams.forEach(team -> team.setPoints((int) (Math.random() * 10)));
    }
}

