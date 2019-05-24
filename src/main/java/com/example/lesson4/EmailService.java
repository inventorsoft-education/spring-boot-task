package com.example.lesson4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;



@Component
public class EmailService implements CommandLineRunner {

    private EmailSender emailSender;

    @Autowired
    public EmailService(EmailSender emailSender){
        this.emailSender = emailSender;
    }

    @Override
    public void run(String... args){
        emailSender.sendScheduledEmail();
    }
}
