package com.example.lesson4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

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

    static private final String EMAIL_REGEXP = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    static private final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

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
        System.out.println("Date in format 'dd-MM-yyyy HH:mm' :");
        simpleMailMessage.setSentDate(dateMaker(scanner));
        scanner.close();
        return simpleMailMessage;
    }

    static private boolean emailValidator(String emailAddr){
        return Pattern.compile(EMAIL_REGEXP, Pattern.CASE_INSENSITIVE).matcher(emailAddr).find();
    }

    private Boolean dateValidator(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try {
            dateFormat.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private Date dateMaker(Scanner scanner){

        String dateString = scanner.nextLine();

        while (!dateValidator(dateString)){
            System.out.println("Please, be carefully! DATE format is 'dd-MM-yyyy HH:mm' :");
            dateString = scanner.nextLine();
        }

        try {
            return (new SimpleDateFormat(DATE_FORMAT)).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }


    static private String emailMaker(Scanner scanner) {
        String email = scanner.nextLine();

        while ( ! emailValidator(email) ) {
            System.out.println("Please, be carefully! Email is invalid : ");
            email = scanner.nextLine();
        }
        return email;
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
