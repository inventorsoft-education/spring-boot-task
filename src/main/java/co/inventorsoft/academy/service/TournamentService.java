package co.inventorsoft.academy.service;

import co.inventorsoft.academy.interfaces.ColorText;
import co.inventorsoft.academy.model.Team;
import co.inventorsoft.academy.dao.TeamDAO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentService implements ColorText {
    TeamDAO teamsList;
    FileWriterService fileWriter;

    /**
     * This method create tournament,write result to console and data.csv file
     */
    public void start() {
        System.out.println("*****************************************************" + GREEN +
                "Teams list" + RESET + "*****************************************************" + PURPLE);
        teamsList.createCorrectPool(); //create 8 teams to play tournament
        System.out.println(RESET + "************************************************************" +
                "********************************************************");
        System.out.println("*****" + GREEN + "Round" + RESET + "************************************"
                + GREEN + "Team 1" + RESET + "***************" + GREEN + "Team 2" + RESET
                + "**********************************" + GREEN + "Score" + RESET + "****");
        while (teamsList.size() != 1) {
            if (teamsList.size() == 2) {//final
                teamsList.generateNewPoints();
                calculateResult("Final");
                winner();
            } else if (teamsList.size() == 4) {//semi-final
                teamsList.generateNewPoints();
                calculateResult("1/2");
            } else if (teamsList.size() == 8) { // start tournament
                fileWriter.write("Round, Team 1, Team 2, Score");
                calculateResult("1/4");
            }
            System.out.println("************************************************************" +
                    "********************************************************");
        }
    }

    /**
     * This method display and write to file all matches in current round
     *
     * @param round name of round
     */
    private void calculateResult(String round) {
        int size = teamsList.size();
        for (int i = 0; i < size; i += 2) {
            Team team1 = teamsList.getTeam(size - 1 - i);
            Team team2 = teamsList.getTeam(size - 2 - i);
            fileWriter.write(round + ", " + team1.getName() + ", " + team2.getName() + ", "
                    + team1.getPoints() + ":" + team2.getPoints());
            System.out.println(getWinner(teamsList.getTeam(size - 1 - i),
                    teamsList.getTeam(size - 2 - i), round));
        }
    }

    /**
     * This method calculate winner from a pair of teams
     *
     * @param teamFirst  first team
     * @param teamSecond second team
     * @param round      actual round name
     * @return String value of result
     */
    public String getWinner(Team teamFirst, Team teamSecond, String round) {
        String result = String.format("*" + BLUE + "%8s\t" + RESET + "   * " + YELLOW
                        + "%40s - %-40s" + RESET + " * " + CYAN + "%5s:%-5s" + RESET + " *",
                round, teamFirst.getName(), teamSecond.getName(),
                teamFirst.getPoints(), teamSecond.getPoints());
        //delete loser team
        teamsList.deleteTeam(teamFirst.getPoints() > teamSecond.getPoints() ? teamSecond : teamFirst);
        return result;
    }

    /**
     * This method display result of tournament
     */
    public void winner() {
        String winner = teamsList.getTeam(0).getName();
        System.out.println("****************************************************" +
                "****************************************************************");
        System.out.println(GREEN_BOLD_BRIGHT + "\t\t\t\t\t\t\t\tThe Winner of tournament is " + winner);
        System.out.println(RESET + "****************************************************" +
                "****************************************************************");
    }
}