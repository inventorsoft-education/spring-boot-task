package demohometask.demohometask;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {
    private static int round;
    private static int numberOfRounds = 3;
    private static String score;


    public static void start(){
        List<Team> listOfTeams = TeamManaging.createListOfTeams();
        TeamManaging.printListOfTeams(listOfTeams);
        System.out.println("\nLet's start playing :D");
        printResult(listOfTeams);
    }

    public static Team startTournament(Team firstTeam, Team secondTeam){
        int randomValue = (int) (Math.random()*(1+1)) + 1;
        if(randomValue == 1){
            return firstTeam;
        }else{
            return secondTeam;
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
            FileOperation.writeObject(winner);
            start++;
            end--;
        }
        return returnBracket;
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
