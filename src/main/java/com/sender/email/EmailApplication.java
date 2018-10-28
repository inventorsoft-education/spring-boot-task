package com.sender.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
public class EmailApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmailApplication.class, args);

        EmailService emailService = context.getBean(EmailService.class);
        try {
            emailService.getEmail().inputData();
            emailService.send();
        } catch (Exception e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }
}
