package com.game_controllers;

import com.DAO.TeamDAO;
import com.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class RegistrationController {
    private TeamDAO teamDAO;
    private TournamentController tournamentController;

    public void startRegistration(){
        System.out.println("Input number of rounds:");
        Scanner scanner=new Scanner(System.in);
        int rounds=scanner.nextInt();
        System.out.println("Number of participants:" + (int)Math.pow(2,rounds));
        List<Team> participants = new ArrayList<>();
        Team team=null;
        generalLoop:
        while(true){
            System.out.println("Choose option:\n1-Add new team\n2-Add new team from database\n" +
                    "3-Fill tournament with teams from database\n4-Add new team to database\n" +
                    "5-See current participant\n6-Back to main menu");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    participants.add(this.newTeamInput());
                    break;
                case 2:
                    team=getUniqueTeamFromDatabase(participants);
                    if(team==null) System.out.println("Database has no team, that is not participants already.");
                            else participants.add(team);
                    break;
                case 3:
                    participants=fillTeamListFromDataBase(participants,(int)(Math.pow(2,rounds)));
                    break;
                case 4:
                    team=this.newTeamInput();
                    if(!teamDAO.has(team)) teamDAO.add(team); else System.out.println("Team is already exist");
                    break;
                case 5:
                    for (Team participant : participants){
                        System.out.println(participant.toString());
                    }
                    break;
                case 6:
                    break generalLoop;

            }
            if(participants!=null&&participants.size()==(int)Math.pow(2,rounds))
                tournamentController.startTournament(participants,rounds);
        }

    }
    private Team newTeamInput(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter team name:");
        String teamName = scanner.nextLine();
        System.out.println("Enter team coach:");
        String coach = scanner.nextLine();
        System.out.println("Enter team captain:");
        String captain = scanner.nextLine();
        return new Team(teamName,captain,coach);
    }
    private List<Team> fillTeamListFromDataBase(List<Team> participants,int size){
        List<Team> unique=getUniqueTeamsFromDatabase(participants);
        int participantNeeded=size - participants.size();
        if(unique.size()<participantNeeded){
            System.out.println("Database has not enouth unique teams to fill DB.");
            return participants;
        }
        List<Team> result=new ArrayList<>();
        result.addAll(participants);
        for(int i=0;i<participantNeeded;i++){
            Team participant=unique.get((int)(Math.random()*unique.size()));
            result.add(participant);
            unique.remove(participant);
        }
        return result;
    }
    private Team getUniqueTeamFromDatabase(List<Team> participants){
        List<Team> resulted=getUniqueTeamsFromDatabase(participants);
        if(resulted==null) return null;
        return resulted.get((int)(Math.random()*resulted.size()));
    }
    private List<Team> getUniqueTeamsFromDatabase(List<Team> participants){
        List<Team> data = teamDAO.getData();
        List<Team> resulted=new ArrayList<>();
        for(Team team:data){
            boolean flag=true;
            for (Team participant:participants){
                if(team.getTeamName().toLowerCase(Locale.ROOT).equals
                        (participant.getTeamName().toLowerCase(Locale.ROOT))) flag=false;
            }
            if(flag) resulted.add(team);
        }
        if(resulted.size()==0) return null;
        return resulted;
    }
}
