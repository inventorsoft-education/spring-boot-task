package com.example.tournament.Service;


import com.example.tournament.DTO.TournamentDto;
import com.example.tournament.Entity.Match;
import com.example.tournament.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class TournamentService {
    private TournamentDto tournamentDto = new TournamentDto();
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private boolean exit;
    private FileSaveService fileSaveService;
    private PrintTournamentMenu printTournamentMenu;

    @Autowired
    public TournamentService(FileSaveService fileSaveService, PrintTournamentMenu printTournamentMenu) {
        this.fileSaveService = fileSaveService;
        this.printTournamentMenu = printTournamentMenu;
    }

    public void runProgram() throws IOException {
        List<Team> teamList = new ArrayList<>();
        List<Match> matchList = new ArrayList<>();
        while (!exit) {
            printTournamentMenu.printMenu();
            int choiceOfAction = getInputAction();
            performActionByChoice(choiceOfAction, teamList, matchList);
        }
    }

    public List<Team> saveTeamToList(String teamName, String coachOfTeam, String captainOfTeam, List<Team> teamList) {
        Team team = new Team(teamName, coachOfTeam, captainOfTeam);
        teamList.add(team);
        return teamList;
    }

    private void performActionByChoice(int choiceAction, List<Team> teamList, List<Match> matchList) throws IOException {
        switch (choiceAction) {
            case 0:
                exit = true;
                System.out.println("You exit from application");
                break;
            case 1:
                List<Team> copyTeamList = new ArrayList<>();
                tournamentDto.setTeams(copyTeamList);
                fileSaveService.saveTeamsToFile("myObjects.txt", tournamentDto.getTeams());
                addTeamToTournament(teamList);
                break;
            case 2:
                if (matchList.size() != 1) {
                    if (teamList.size() % 2 == 0 && teamList.size() % 3 != 0) {
                        generateRandomMatches(teamList, matchList);
                        System.out.println(matchList + "\n");
                        System.out.println("**********************************");
                    } else {
                        System.out.println("Please add team\n");
                        System.out.println("**********************************");
                    }
                    tournamentDto.setMatches(matchList);
                    fileSaveService.saveMatchesToFile("myObjects.txt", tournamentDto.getMatches());
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
                    fileSaveService.saveMatchesToFile("myObjects.txt", tournamentDto.getMatches());
                } else {
                    System.out.println("*****The tournament is over*****");
                }
                break;
            case 4:
                if (matchList.size() > 1) {
                    generateMatchWithRandomResult(generateMatchOfWinnerTeam(matchList));
                    tournamentDto.setMatches(matchList);
                    fileSaveService.saveMatchesToFile("myObjects.txt", tournamentDto.getMatches());
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
                    fileSaveService.saveTeamToFile("myObjects.txt", tournamentDto.getTeam());
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

    public void addTeamToTournament(List<Team> teamList) {
        System.out.println("Enter name team :->");
        String teamName = sc.nextLine();
        System.out.println("Enter coach of team :->");
        String coachOfTeam = sc.nextLine();
        System.out.println("Enter captain of team:->");
        String captainOfTeam = sc.nextLine();
        saveTeamToList(teamName, coachOfTeam, captainOfTeam, teamList);
    }

    public List<Match> generateRandomMatches(List<Team> teams, List<Match> matchList) {
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

    public List<Match> generateMatchWithRandomResult(List<Match> matchList) {
        int scoreFirstTeam;
        int scoreSecondTeam;
        String round = "1/" + matchList.size();
        StringBuilder scoreString = new StringBuilder();
        for (int i = 0; i < matchList.size(); i++) {
            scoreString.setLength(0);
            scoreFirstTeam = random.ints(1, 10).findFirst().getAsInt();
            scoreSecondTeam = random.ints(1, 10).findFirst().getAsInt();
            if (scoreFirstTeam > scoreSecondTeam) {
                matchList.get(i).setWinner(matchList.get(i).getTeam1());
            } else {
                matchList.get(i).setWinner(matchList.get(i).getTeam2());
            }
            scoreString.append(scoreFirstTeam).append(':').append(scoreSecondTeam);
            matchList.get(i).setScore(String.valueOf(scoreString));
            matchList.get(i).setRound(round);
        }
        return matchList;
    }

    public List<Match> generateMatchOfWinnerTeam(List<Match> matches) {
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            teams.add(matches.get(i).getWinner());

        }
        return generateMatches(teams, matches);
    }

    public List<Match> generateMatches(List<Team> teams, List<Match> matchList) {
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


}
