package main;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {
    String teamName;
    String horse;
    String rider;

    @Override
    public String toString() {
        return teamName + ", horse - " + horse + ", rider - " + rider;
    }

}
