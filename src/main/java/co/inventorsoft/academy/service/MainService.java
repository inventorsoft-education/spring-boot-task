package co.inventorsoft.academy.service;

import co.inventorsoft.academy.interfaces.ColorText;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MainService implements ColorText {
    private TournamentService tournament;
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
        registration.createTeam(); //registration service
        tournament.start();// start tournament
    }
}

