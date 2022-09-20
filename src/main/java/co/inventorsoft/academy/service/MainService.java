package co.inventorsoft.academy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static co.inventorsoft.academy.enums.ColorText.RESET;
import static co.inventorsoft.academy.enums.ColorText.GREEN;

@Component
@AllArgsConstructor
public class MainService {
    /**
     * In this tournament service we start tournament and calculate winner
     */
    private TournamentService tournament;
    /**
     * In this registration service we add new team to tournament
     */
    private RegistrationService registration;

    /**
     * This method display table header
     */
    public void mainScreen() {
        System.out.println("************************************************************" +
                "********************************************************");
        System.out.println(GREEN + "\t\t\t\t\t\t\t\t\t\t\t\t\tWelcome!!");
        System.out.println("\t\t\t\t\tPlease, register your team to derby with Formula1 teams in 1vs1 format");
        System.out.println(RESET + "************************************************************" +
                "********************************************************");
        registration.createTeam();
        tournament.start();
    }
}

