package co.inventorsoft.academy.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Team {
    private String name;
    private String pilot1;
    private String pilot2;
    private Integer points = (int) (Math.random() * 10);

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Team(String name, String pilot1, String pilot2) {
        this.name = name;
        this.pilot1 = pilot1;
        this.pilot2 = pilot2;
    }

    public String toString() {
        return "Team: " + this.getName() +
                ", pilot #1: " + this.getPilot1() +
                ", pilot #2: " + this.getPilot2() ;
    }
}

