package com.example.demo;

import com.example.demo.Model.Email;
import com.example.demo.Service.EmailService;
import com.example.demo.Utils.Validator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class ConsoleProcessor implements CommandLineRunner {

    private EmailService emailService;
    private Validator validator;

    public ConsoleProcessor(EmailService emailService, Validator validator) {
        this.emailService = emailService;
        this.validator = validator;
    }

    @Override
    public void run(String... args) throws Exception {

        Boolean alive = true;
        while (alive){
            System.out.println("What would you like to do? \nType in console to:");
            System.out.println("1 - write new e-mail");
            System.out.println("2 - print all emails");
            System.out.println("3 - print only sent emails");
            System.out.println("4 - print only unsent emails");
            System.out.println("anything else - exit");
            Scanner consoleScanner = new Scanner(System.in);
            String input = consoleScanner.nextLine();
            switch (input){
                case "1":

                    System.out.println("Enter recipient:");
                    String recipient = "";
                    Boolean validRecipient = false;
                    while (!validRecipient){
                        recipient = consoleScanner.nextLine();
                        if (validator.isEmailValid(recipient)){
                            validRecipient = true;
                        } else {
                            System.out.println("E-mail address " + recipient + " is not valid. Please try again: ");
                        }
                    }

                    System.out.println("Enter subject:");
                    String subject = consoleScanner.nextLine();


                    System.out.println("Enter body:");
                    String body = consoleScanner.nextLine();

                    System.out.println("Enter date(dd-mm-yyyy hh:mm):");
                    String date = "";
                    Boolean validDate = false;
                    while (!validDate){
                        date = consoleScanner.nextLine();
                        //date = "21-01-2019 18:54";
                        if (validator.isDateValid(date)){
                            validDate = true;
                        } else {
                            System.out.println("Date " + date + " is not valid. Please try again: ");
                        }
                    }

                    Email email = new Email(recipient, subject, body, validator.parseDate(date), false);
                    emailService.add(email);
                    break;
                case "2":
                    System.out.println(emailService.getAllEmails());
                    break;
                case "3":
                    System.out.println(emailService.getSentEmails());
                    break;
                case "4":
                    System.out.println(emailService.getUnsentEmails());
                    break;
                default:
                    System.out.println("Farewell, stranger");
                    alive = false;
                    break;
            }
            System.out.println("Press anything to continue...");
            consoleScanner.nextLine();
        }

    }
}
