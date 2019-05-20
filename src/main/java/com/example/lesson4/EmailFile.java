package com.example.lesson4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



@Repository
public class EmailFile {

    @Value("${email.file}")
    private String file;

    SimpleMailMessage simpleMailMessage;
    Scanner scanner;

    public EmailFile(){
        this.simpleMailMessage = new SimpleMailMessage();
        this.scanner = new Scanner(System.in);
    }


    public SimpleMailMessage getEmailFromConsole(){

        System.out.println("Email subject :");
        simpleMailMessage.setSubject( scanner.nextLine() );
        System.out.println("Email body :");
        simpleMailMessage.setText(scanner.nextLine());
        System.out.println("Email recipient :");
        simpleMailMessage.setTo(emailMaker(scanner) );
        System.out.printf("Date in format 'dd-MM-yyyy HH:mm' :");
        simpleMailMessage.setSentDate( dataMaker(scanner) );
        scanner.close();
        return simpleMailMessage;
    }

    static private Date dataMaker(Scanner scanner) {
        //TODO something with wrong date

        String pattern = "dd-MM-yyyy HH:mm";

        try {
            return (new SimpleDateFormat(pattern)).parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    static private String emailMaker(Scanner scanner) {

        //TODO: validate email
        return  scanner.nextLine();
    }

    public void saveEmail(SimpleMailMessage simpleMailMessage) {
        clearEmail();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(simpleMailMessage);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void clearEmail() {
        try {
            Files.deleteIfExists(Paths.get(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createEmail(){
        SimpleMailMessage simpleMailMessage = getEmailFromConsole();
        saveEmail(simpleMailMessage);
    }


}
