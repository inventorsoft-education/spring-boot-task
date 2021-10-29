package com.example.demo;

public class Team {
    private String name;
    private String capitan;
    private String coach;

    public Team() {
    }

    public Team(String name, String capitan, String coach) {
        this.name = name;
        this.capitan = capitan;
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", capitan='" + capitan + '\'' +
                ", coach='" + coach + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapitan() {
        return capitan;
    }

    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
