package demohometask.demohometask;

import java.util.LinkedList;
import java.util.List;

public class TeamManaging {
    public static List<Team> createListOfTeams(){
        String[] names = {"John", "Kevin", "Bruno", "Max", "Mike", "Jack", "Baylor", "Villanova"};
        String[] teams = {"Team_name_1", "Team_name_2", "Team_name_3", "Team_name_4", "Team_name_5", "Team_name_6", "Team_name_7", "Team_name_8"};
        List<Team> listOfTeams = new LinkedList();

        for(int i =0; i<names.length; i++){
            listOfTeams.add(new Team(teams[i], names[i], names[i]));
        }
        return listOfTeams;
    }

    public static void printListOfTeams(List<Team> listOfTeams){
        System.out.println("^^^^^^^ LIST of teams ^^^^^^^");
        for(Team team : listOfTeams){
            System.out.println(team.getName() + ", " + team.getCapitan() + ", " + team.getCoach());
        }
    }
}
