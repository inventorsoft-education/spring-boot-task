package com.example.springboot;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
