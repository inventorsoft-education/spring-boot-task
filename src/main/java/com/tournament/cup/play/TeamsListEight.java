package com.tournament.cup.play;

import com.tournament.cup.details.Squad;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamsListEight {
    List<Squad> squads = new ArrayList<>();

    Squad MU = new Squad("Manchester United", "Ronaldo", "Rangnick");
    Squad MC = new Squad("Manchester City", "De Bruyne", "Pep");
    Squad Chelsea = new Squad("Chelsea", "Thiago Silva", "Thuchel");
    Squad Real = new Squad("Real Madrid", "Modric", "Anchelotti");
    Squad Barca = new Squad("Barcelona", "Busquets", "Xavi");
    Squad Bayern = new Squad("Bayern Munich", "Neuer", "Nagelsmann");
    Squad PSG = new Squad("PSG", "Marquinhos", "Pochettino");


    public void madeList () {
        squads.add(MU);
        squads.add(MC);
        squads.add(Chelsea);
        squads.add(Real);
        squads.add(Barca);
        squads.add(Bayern);
        squads.add(PSG);
    }

    public Boolean add(Squad squad) {
        return squads.add(squad);
    }

    public int sizeEight() {
        return squads.size();
    }

    public Squad getTeamByIndex(Integer num) {
        return squads.get(num);
    }


    public void deleteTeamEight(Squad squad) {
        squads.remove(squad);
    }

}

