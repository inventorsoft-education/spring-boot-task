package com.game_controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@AllArgsConstructor
public class MainController {
    private RegistrationController registrationController;
    private TournamentController tournamentController;

    public  void startApp(){
        System.out.println("Welcome");
        generalLoop:
        while(true){
            System.out.println("Choose option:\n1-Start tournament\n2-View previous tournaments\n3-Quit");
            int option=new Scanner(System.in).nextInt();
            switch (option){
                case 1:
                    registrationController.startRegistration();
                    break;
                case 2:
                    tournamentController.resultsViewer();
                    break;
                case 3:
                    break generalLoop;
            }
        }
    }
}
