package com.tournament.cup.play;

import com.tournament.cup.details.Squad;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamsListSixteen {
    List<Squad> squads = new ArrayList<>();

    Squad Shakhtar = new Squad("Shakhtar", "Pyatov", "De Dzerbi");
    Squad Dynamo = new Squad("Dynamo", "Sydorchuk", "Lucesku");
    Squad Liverpool = new Squad("Liverpool", "Henderson", "Klopp");
    Squad Juventus = new Squad("Juventus", "Chiellini", "Allegri");
    Squad Milan = new Squad("Milan", "Ibra", "Pioli");
    Squad Atletico = new Squad("Atletico", "Koke", "Simeone");
    Squad Arsenal = new Squad("Arsenal", "Arteta", "Wenger");
    Squad Ajax = new Squad("Ajax", "Tadic", "Ten Haag");
    Squad MU = new Squad("Manchester United", "Ronaldo", "Rangnick");
    Squad MC = new Squad("Manchester City", "De Bruyne", "Pep");
    Squad Chelsea = new Squad("Chelsea", "Thiago Silva", "Thuchel");
    Squad Real = new Squad("Real Madrid", "Modric", "Anchelotti");
    Squad Barca = new Squad("Barcelona", "Busquets", "Xavi");
    Squad Bayern = new Squad("Bayern Munich", "Neuer", "Nagelsmann");
    Squad PSG = new Squad("PSG", "Marquinhos", "Pochettino");


    public void madeList () {
        squads.add(Shakhtar);
        squads.add(Dynamo);
        squads.add(Liverpool);
        squads.add(Juventus);
        squads.add(Milan);
        squads.add(Atletico);
        squads.add(Arsenal);
        squads.add(Ajax);
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

    public int sizeSixteen() {
        return squads.size();
    }

    public Squad getTeamByIndex(Integer num) {
        return squads.get(num);
    }


    public void deleteTeamSixteen(Squad squad) {
        squads.remove(squad);
    }

}

