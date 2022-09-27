package Application;

import Application.Managers.FileWriterManager;

public class Pair{

    private final Team team1;
    private final Team team2;
    /**
     * result field contains 0 if pair was not fighting yet
     * 1 if team1 is winner
     * 2 if team2 is winner
     */
    private int result = 0;
    /**
     * number of pair
     */
    private final int pairNumber;

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Team getResult() {
        return result != 0? result == 1? team1 : team2 : null;
    }

    public Pair(Team team1, Team team2, int pairNumber) {
        this.team1 = team1;
        this.team2 = team2;
        this.pairNumber = pairNumber;
    }

    /**
     * This method imitates fight
     * Randomly determines the winner
     */
    public void fight(){
        System.out.println("\nFight begins!");
        System.out.println("Pair number " + pairNumber);
        FileWriterManager.writeSentance("\nPair number " + pairNumber);
        System.out.println("Team " + team1.getName() + " VS " + "Team " + team2.getName());
        FileWriterManager.writeSentance("\nTeam " + team1.getName() + " VS " + "Team " + team2.getName());

        if(((Math.random() * 100) + 1) > 50) {
            getTeam1().setWinner(true);
            setResult(1);
            System.out.println("The battle was hard...and the winner is (Team A) " + getTeam1().getName());
            FileWriterManager.writeSentance("\nThe battle was hard...and the winner is (Team A) " + getTeam1().getName());
        } else {
            getTeam2().setWinner(true);
            setResult(2);
            System.out.println("The battle was hard...and the winner is (Team B) " + getTeam2().getName());
            FileWriterManager.writeSentance("\nThe battle was hard...and the winner is (Team B) " + getTeam2().getName());
        }
    }

    @Override
    public String toString() {
        return "\nApplication.Pair " + pairNumber +
                "\nteam A = " + team1.getName() +
                ", \nteam B = " + team2.getName() + "\n";
    }
}
