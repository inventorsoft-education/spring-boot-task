package demohometask.demohometask;

import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class Match {
    private static int round;
    private static int numberOfRounds = 3;
    private static String score;


    public static List<Team> createListOfTeams(){
        String[] names = {"John", "Kevin", "Bruno", "Max", "Mike", "Jack", "Baylor", "Villanova"};
        String[] teams = {"Team_name_1", "Team_name_2", "Team_name_3", "Team_name_4", "Team_name_5", "Team_name_6", "Team_name_7", "Team_name_8"};
        List<Team> listOfTeams = new LinkedList();

        for(int i =0; i<names.length; i++){
            listOfTeams.add(new Team(teams[i], names[i], names[i]));
        }
        return listOfTeams;
    }

    public static void start(){
        List<Team> listOfTeams = createListOfTeams();
        printListOfTeams(listOfTeams);
        System.out.println("\nLet's start playing :D");
        printResult(listOfTeams);
    }

    public static void writeObject(Team object){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\IdeaProjects\\InventorSoftProjects\\demo-home-task\\src\\main\\java\\demohometask\\demohometask\\team.txt")))
        {
            oos.writeObject(object);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void printListOfTeams(List<Team> listOfTeams){
        System.out.println("^^^^^^^ LIST of teams ^^^^^^^");
        for(Team team : listOfTeams){
            System.out.println(team.getName() + ", " + team.getCapitan() + ", " + team.getCoach());
        }
    }

    public static List<Team> play(List<Team> listOfTeams){
        String[] scores = {"2:0", "3:2", "4:1", "1:0", "2:1"};
        List<Team> returnBracket = new ArrayList<>();
        int start = 0;
        int end = (listOfTeams.size() - 1);
        while (start < end) {
            Team winner = startTournament(listOfTeams.get(start), listOfTeams.get(end));
            score = scores[new Random().nextInt(scores.length)];
            System.out.printf("%s/%d, %s, %s, %s\n", round, numberOfRounds, listOfTeams.get(start).getName(), listOfTeams.get(end).getName(), score);
            returnBracket.add(winner);
            writeObject(winner);
            start++;
            end--;
        }
        return returnBracket;
    }

    public static Team startTournament(Team firstTeam, Team secondTeam){
        int randomValue = (int) (Math.random()*(1+1)) + 1;
        if(randomValue == 1){
            return firstTeam;
        }else{
            return secondTeam;
        }
    }

    public static void printResult(List<Team> listOfTeams){
        System.out.println("Round" + ", " + "Team 1"+ ", " +"Team 2"+ ", " +"Score");
        round++;
        List<Team> roundOne = play(listOfTeams);
        round++;
        List<Team> roundTwo = play(roundOne);
        round++;
        List<Team> roundThree = play(roundTwo);

        System.out.println("Winner: " + roundThree.toString());
    }

}
