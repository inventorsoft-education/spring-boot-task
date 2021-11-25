package com.example.springboot;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team {
    String name;
    String capitan;
    String coach;

    @Override
    public String toString() {
        return name + ", capitan - " + capitan + ", coach - " + coach;
    }
}
