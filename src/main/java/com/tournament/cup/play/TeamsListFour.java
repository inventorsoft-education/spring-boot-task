package com.tournament.cup.play;

import com.tournament.cup.details.Squad;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamsListFour {
    List<Squad> squads = new ArrayList<>();

    Squad MU = new Squad("Manchester United", "Ronaldo", "Rangnick");
    Squad MC = new Squad("Manchester City", "De Bruyne", "Pep");
    Squad Chelsea = new Squad("Chelsea", "Thiago Silva", "Thuchel");


    public void madeList() {
        squads.add(MU);
        squads.add(MC);
        squads.add(Chelsea);

    }

    public Boolean add(Squad squad) {
        return squads.add(squad);
    }

    public int sizeFour() {
        return squads.size();
    }

    public Squad getTeamByIndex(Integer num) {
        return squads.get(num);
    }


    public void deleteTeamFour(Squad squad) {
        squads.remove(squad);
    }

}

