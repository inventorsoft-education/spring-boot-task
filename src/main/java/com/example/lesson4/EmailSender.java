package com.example.lesson4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Date;

@Component
public class EmailSender implements Runnable{

    private JavaMailSender javaMailSender;
    private TaskScheduler taskScheduler;
    private EmailFile emailFile;

    @Value("${email.file}")
    private String file;

    @Autowired
    public EmailSender(TaskScheduler taskScheduler, JavaMailSender javaMailSender, EmailFile emailFile){
        this.javaMailSender = javaMailSender;
        this.taskScheduler = taskScheduler;
        this.emailFile = emailFile;
    }


    public void sendScheduledEmail() {
        SimpleMailMessage email = emailFile.getEmailFromFile();
        if(email == null){
            emailFile.createEmail();
        }
        taskScheduler.schedule(() -> run(), email.getSentDate().compareTo(new Date()) <= 0 ? new Date() : email.getSentDate());
    }

    @Override
    public void run() {
        SimpleMailMessage email = null;
        email = emailFile.getEmailFromFile();
        if(email != null){
            javaMailSender.send(email);
        }
        else {
        System.out.println("Sorry, email doesn't find!");
        }
        System.exit(0);
    }
}
