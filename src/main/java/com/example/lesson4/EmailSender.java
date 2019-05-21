package com.example.lesson4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;

@Component
public class EmailSender implements Runnable{

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private TaskScheduler taskScheduler;

    @Value("${email.file}")
    private String file;


    public SimpleMailMessage getEmail(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (SimpleMailMessage) ois.readObject();
        } catch (Exception ex) {
            return null;
        }
    }


    public void sendScheduledEmail() {

        SimpleMailMessage email = getEmail();
        taskScheduler.schedule(() -> run(), email.getSentDate().compareTo(new Date()) <= 0 ? new Date() : email.getSentDate());
    }

    @Override
    public void run() {
        SimpleMailMessage email = null;
        email = getEmail();
        if(email != null){
            javaMailSender.send(email);
        }
        else {
        System.out.println("Sorry, email doesn't find!");
        }
        System.exit(0);
    }
}
