package demohometask.demohometask;

import java.io.Serializable;
import java.util.Objects;

public class Team implements Serializable {
    private String name;
    private String capitan;
    private String coach;

    public Team(){}

    public Team(String name, String capitan, String coach) {
        this.name = name;
        this.capitan = capitan;
        this.coach = coach;
    }

    public String getName() {
        return name;
    }

    public String getCapitan() {
        return capitan;
    }

    public String getCoach() {
        return coach;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return name + ", " + capitan + ", " + coach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return getName().equals(team.getName()) &&
                getCapitan().equals(team.getCapitan()) &&
                getCoach().equals(team.getCoach());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCapitan(), getCoach());
    }
}
