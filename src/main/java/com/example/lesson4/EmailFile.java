package com.example.lesson4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;


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

    public void createEmail(){
        SimpleMailMessage simpleMailMessage = getEmailFromConsole();
        saveEmail(simpleMailMessage);
    }


    public SimpleMailMessage getEmailFromConsole(){

        System.out.println("Email subject :");
        simpleMailMessage.setSubject( scanner.nextLine() );
        System.out.println("Email body :");
        simpleMailMessage.setText(scanner.nextLine());
        System.out.println("Email recipient :");
        simpleMailMessage.setTo(EmailController.emailMaker(scanner));
        System.out.println("Date in format 'dd-MM-yyyy HH:mm' :");
        simpleMailMessage.setSentDate(DateController.dateMaker(scanner));
        return simpleMailMessage;
    }

    public SimpleMailMessage getEmailFromFile(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (SimpleMailMessage) ois.readObject();
        } catch (Exception ex) {
            return null;
        }
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

    @PreDestroy
    private void scannerClose(){
        scanner.close();    
    }

}
