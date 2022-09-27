package Application.Managers;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * This class is managing regular things related to game details
 * such as:
 * welcoming with user
 * how many teams will be in game
 * check if user entered legal number of teams
 * and also adding teams
 */
@Data
@Component
public class GameManager {

    TeamManager tm;
    private int number = 0;

    /**
     * This method is the main flow of the game
     * it calls method which adds the teams
     * then it calls method which will make pairs
     * and then this method calls methods which determines the winner
     *
     */
    public void gameFlow() {
        System.out.println("The game begins!");
        addingTeams();
        tm.getPairs();
        while (tm.isInGame()) {
            tm.fight();
            tm.clearPairs();
        }
    }

    /**
     * This method adding teams.
     * As much as user entered.
     */
    private void addingTeams(){
        teamNums();
        for (int i = 0; i < number; i++) {
            tm.addTeam();
        }
    }

    @Autowired
    public void setTm(TeamManager tm) {
        this.tm = tm;
    }

    /**
     * This method asking number of teams
     * and passes it to numCheck
     * if numCheck return false
     * then this method recalls itself
     *
     */
    private void teamNums() {
        Scanner scanner = new Scanner(System.in);
        greetings();
        try {
            number = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("You entered not number");
            teamNums();
        }
        if (!numCheck(number)) {
            teamNums();
        }
    }

    /**
     * this method accept number of teams which was entered by user
     * and check if it is legal
     * return true if legal
     * return false is illegal
     * @param x
     * @return try if legal and false if illegal
     */
    private boolean numCheck(int x) {

        double i = Math.log(x) / Math.log(2);
        return x >= 4 && i == Math.floor(i);
    }

    /**
     * this method welcomes user
     */
    private void greetings() {
        System.out.println("Hello!");
        System.out.println("How many teams will be participating this tournament?");
        System.out.println("(Number should be power of 2 and at least 4)");
    }

}
