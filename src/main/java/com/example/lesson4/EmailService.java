package com.example.lesson4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;



@Component
public class EmailService implements CommandLineRunner {

    @Autowired
    private EmailFile emailFile;

    @Autowired
    private EmailSender emailSender;

    @Override
    public void run(String... args){
        emailFile.createEmail();
        emailSender.sendScheduledEmail();
    }
}
