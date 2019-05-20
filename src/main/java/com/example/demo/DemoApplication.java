package com.example.demo;

import com.example.demo.Model.Email;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {


    private EmailService emailService;
    private Validator validator;

    public DemoApplication(EmailService emailService, Validator validator) {
        this.emailService = emailService;
        this.validator = validator;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        System.out.println("What would you like to do? \nType in console to:");
        System.out.println("1 - write new e-mail");
        System.out.println("2 - print all emails");
        System.out.println("3 - print only sent emails");
        System.out.println("4 - print only unsent emails");
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
                break;
        }
    }
}
