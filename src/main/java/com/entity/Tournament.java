package com.entity;

import com.entity.Game;
import com.entity.Team;
import lombok.Getter;

import javax.swing.text.TabExpander;
import java.util.*;

public class Tournament {

    private List<Team> teamList;
    @Getter
    private int rounds;
    @Getter
    private List<String[]> playedMatches= new ArrayList<>();

    public Tournament(List<Team> teamList, int rounds){
        Collections.shuffle(teamList,new Random());
        this.teamList=teamList;
        this.rounds=rounds;
    }
    public void playRound(){
        ArrayList<Team> winners=new ArrayList<>();
        if(rounds>0){
            for(int i=0;i<teamList.size();i+=2){
                Team firstTeam=teamList.get(i);
                Team secondTeam=teamList.get(i+1);
                Game currentMatch=new Game(firstTeam,secondTeam,rounds);
                winners.add(currentMatch.play());
                playedMatches.add(currentMatch.getResult());
            }
            this.teamList=winners;
            this.rounds-=1;
        }
    }
    public void endTournament(){
        while (rounds>0){
            playRound();
        }
    }
    public String getStringResult(){
        String result="";
        for(String[] match:playedMatches){
            result+=match[0]+", "+match[1]+", "+match[2]+", "+match[3]+"\n";
        }
        return result;
    }

}
