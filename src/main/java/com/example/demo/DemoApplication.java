package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    public static ArrayList<Email> emails = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        String flag = " ";


        do {
            System.out.println("Would you like to add new message?" + "y/n");
            Scanner in = new Scanner(System.in);
            flag = in.nextLine();

            if (flag.equals("n")){
                break;
            }
            Email email=new Email();
            email.inputData();
            WorkWithJsonFile test=new WorkWithJsonFile();
            test.saveEmailToJson(email);
        }
        while(!flag.equals("n"));


    }
}


