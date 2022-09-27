package Application;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {
    @NonNull
     String captain;
    @NonNull
     String coach;
    @NonNull
     String name;

    boolean isWinner;

    public String getCaptain() {
        return captain;
    }

    public String getCoach() {
        return coach;
    }

    public String getName() {
        return name;
    }
    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public Team(@NonNull String captain, @NonNull String coach, @NonNull String name) {
        this.captain = captain;
        this.coach = coach;
        this.name = name;
    }
}
