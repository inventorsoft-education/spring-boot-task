package com.example.tournament.Service;


import com.example.tournament.DTO.TournamentDto;
import com.example.tournament.Entity.Match;
import com.example.tournament.Entity.Team;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class TournamentService {
    private List<Team> teamList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private TournamentDto tournamentDto = new TournamentDto();
    private FileSaveService fileSaveService;
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private boolean exit;

    @Autowired
    public TournamentService(FileSaveService fileSaveService) {
        this.fileSaveService = fileSaveService;
    }

    public void runProgram() throws IOException {
        while (!exit) {
            printTournamentMenu();
            int choiceOfAction = getInputAction();
            performActionByChoice(choiceOfAction);
        }
    }

    public List<Team> saveTeamToList(String teamName, String coachOfTeam, String captainOfTeam) {
        Team team = new Team(teamName, coachOfTeam, captainOfTeam);
        teamList.add(team);
        return teamList;
    }

    public void printTournamentMenu() {
        System.out.println("If you want to add team enter:-> 1");
        System.out.println("if you want to generate tournament matches enter:-> 2");
        System.out.println("if you want to generate result for matches enter:-> 3");
        System.out.println("if you want to continue generate matches enter:-> 4");
        System.out.println("if you want to see a winner enter:-> 5");

    }

    private void performActionByChoice(int choiceAction) throws IOException {
        switch (choiceAction) {
            case 0:
                exit = true;
                System.out.println("You exit from application");
                break;
            case 1:
//                teamList.add(new Team("real", "dcs", "sdvc"));
//                teamList.add(new Team("barca", "dcs", "sdvc"));
//                teamList.add(new Team("dynamo", "dcs", "sdvc"));
//                teamList.add(new Team("shachtar", "dcs", "sdvc"));
//                teamList.add(new Team("roma", "dcs", "sdvc"));
//                teamList.add(new Team("chelsea", "dcs", "sdvc"));
//                teamList.add(new Team("mu", "dcs", "sdvc"));
//                teamList.add(new Team("atletico", "dcs", "sdvc"));
                List<Team> copyTeamList = new ArrayList<>(teamList);
                tournamentDto.setTeams(copyTeamList);
                fileSaveService.saveToFile("myObjects.txt", objectMapper.writeValueAsString(tournamentDto));
                addTeamToTournament();
                break;
            case 2:
                if (matchList.size() != 1) {
                    if (teamList.size() % 2 == 0 && teamList.size() % 3 != 0) {
                        generateRandomMatches(teamList);
                        System.out.println(matchList + "\n");
                        System.out.println("**********************************");
                    } else {
                        System.out.println("Please add team\n");
                        System.out.println("**********************************");
                    }
                    tournamentDto.setMatches(matchList);
                    fileSaveService.saveToFile("myObjects.txt", objectMapper.writeValueAsString(tournamentDto));
                } else {
                    System.out.println("*****The tournament is over*****");
                }
                break;
            case 3:
                if (matchList.size() != 1) {
                    if (!matchList.isEmpty()) {
                        generateMatchWithRandomResult(matchList);
                        System.out.println(matchList + "\n");
                        System.out.println("**********************************");
                    } else {
                        System.out.println("Generate matches for tournament please");
                    }
                    tournamentDto.setMatches(matchList);
                    fileSaveService.saveToFile("myObjects.txt", objectMapper.writeValueAsString(tournamentDto));
                } else {
                    System.out.println("*****The tournament is over*****");
                }
                break;
            case 4:
                if (matchList.size() > 1) {
                    generateMatchWithRandomResult(generateMatchOfWinnerTeam(matchList));
                    tournamentDto.setMatches(matchList);
                    fileSaveService.saveToFile("myObjects.txt", objectMapper.writeValueAsString(tournamentDto));
                    System.out.println(matchList + "\n");
                    System.out.println("**********************************");
                } else {
                    System.out.println(matchList);
                    System.out.println("All matches are played, to view the winner go to the 5th item of the program \n");
                    System.out.println("**********************************");
                }
                break;
            case 5:
                if (matchList.size() == 1) {
                    Team team = getWinnerOfTournament(matchList);
                    System.out.println("Winner team -> " + team + "\n");
                    tournamentDto.setTeam(team);
                    fileSaveService.saveToFile("myObjects.txt", objectMapper.writeValueAsString(tournamentDto));
                } else {
                    System.out.println("Matches not played are available");
                }
        }
    }


    private int getInputAction() {
        int choiceOfAction = -1;
        while (choiceOfAction < 0 || choiceOfAction > 5) {
            System.out.print("\nEnter your choice of Action: ");
            choiceOfAction = Integer.parseInt(sc.nextLine());

        }
        return choiceOfAction;
    }

    public void addTeamToTournament() {
        System.out.println("Enter name team :->");
        String teamName = sc.nextLine();
        System.out.println("Enter coach of team :->");
        String coachOfTeam = sc.nextLine();
        System.out.println("Enter captain of team:->");
        String captainOfTeam = sc.nextLine();
        saveTeamToList(teamName, coachOfTeam, captainOfTeam);
    }

    public List<Match> generateRandomMatches(List<Team> teams) {
        while (!teams.isEmpty()) {
            Team firsTeam = teams.get(random.nextInt(teams.size()));
            teams.remove(firsTeam);
            Team secondTeam = teams.get(random.nextInt(teams.size()));
            teams.remove(secondTeam);
            Match match = new Match("", firsTeam, secondTeam, "", null);
            matchList.add(match);
        }
        return matchList;
    }

    public List<Match> generateMatchWithRandomResult(List<Match> matches) {
        int scoreFirstTeam;
        int scoreSecondTeam;
        String round = "1/" + matchList.size();
        StringBuilder scoreString = new StringBuilder();
        for (int i = 0; i < matches.size(); i++) {
            scoreString.setLength(0);
            scoreFirstTeam = random.ints(1, 10).findFirst().getAsInt();
            scoreSecondTeam = random.ints(1, 10).findFirst().getAsInt();
            if (scoreFirstTeam > scoreSecondTeam) {
                matches.get(i).setWinner(matches.get(i).getTeam1());
            } else {
                matches.get(i).setWinner(matches.get(i).getTeam2());
            }
            scoreString.append(scoreFirstTeam).append(':').append(scoreSecondTeam);
            matches.get(i).setScore(String.valueOf(scoreString));
            matches.get(i).setRound(round);
        }
        return matches;
    }

    public List<Match> generateMatchOfWinnerTeam(List<Match> matches) {
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            teams.add(matches.get(i).getWinner());

        }
        return generateMatches(teams);
    }

    public List<Match> generateMatches(List<Team> teams) {
        matchList.removeAll(matchList);
        String round = "1/" + teams.size();
        for (int i = 0; i < teams.size() / 2 + 1; i++) {
            Match match = new Match(round, teams.get(i), teams.get(i + 1), "", null);
            matchList.add(match);
            i++;
        }
        return matchList;
    }

    public Team getWinnerOfTournament(List<Match> matches) {
        return matches.get(0).getWinner();
    }
//}

}
