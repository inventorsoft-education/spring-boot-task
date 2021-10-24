package com.example.task5.model;

import java.util.Objects;

public class Team {
    private String name;
    private String captain;
    private String coach;

    public String getName() {
        return name;
    }

    public Team(String name, String captain, String coach) {
        this.name = name;
        this.captain = captain;
        this.coach = coach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name) &&
                captain.equals(team.captain) &&
                coach.equals(team.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, captain, coach);
    }

    @Override
    public String toString() {
        return  name +
                ", captain= " + captain +
                ", coach= " + coach;
    }
}
