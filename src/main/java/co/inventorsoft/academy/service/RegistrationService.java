package co.inventorsoft.academy.service;

import co.inventorsoft.academy.interfaces.ColorText;
import co.inventorsoft.academy.model.Team;
import co.inventorsoft.academy.dao.TeamDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class RegistrationService implements ColorText {
    /**
     * list with team on this tournament
     */
    private TeamDAO teamsList;

    /**
     * This method create teams and add pool of teams (LinkedList)
     */
    public void createTeam() {
        Scanner in = new Scanner(System.in);
        boolean exitFlag = true;
        while (exitFlag) {
            System.out.println(BLUE + "Please choose number of option:\n" + " 1. Create team \n" +
                    " 2. Start tournament\n" + " 3. Exit" + RESET);
            String option = in.nextLine();
            switch (option) {
                /* Add new team to list  */
                case "1":
                    System.out.println("************************************************************" +
                            "********************************************************");
                    System.out.print(GREEN + "Input name of Team: " + RESET);
                    String name = in.nextLine();
                    System.out.print(GREEN + "Input pilot #1 name: " + RESET);
                    String pilot1 = in.nextLine();
                    System.out.print(GREEN + "Input pilot #2 name: " + RESET);
                    String pilot2 = in.nextLine();
                    teamsList.add(new Team(name, pilot1, pilot2));
                    System.out.println("************************************************************" +
                            "********************************************************");
                    break;
                /* End registration and go to next step (start tournament) */
                case "2":
                    exitFlag = false;
                    break;
                /*  Exit of application */
                case "3":
                    System.out.println("******************************************************" +
                            GREEN_BOLD_BRIGHT + " Goodbye!" + RESET +
                            " ****************************************************");
                    System.exit(0);
                    break;
                /*  Wrong input case */
                default:
                    System.out.println(RED_BOLD_BRIGHT + "Wrong input parameter! Go to next step." + RESET);
                    exitFlag = false;
                    break;
            }
        }
        System.out.println("************************************************************" +
                "********************************************************");
    }
}
