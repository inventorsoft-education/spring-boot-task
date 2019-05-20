package com.example.lesson4;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;

@Component
public class EmailSender{

    @Autowired
    private JavaMailSender javaMailSender;

    private SimpleMailMessage simpleMailMessage;

    @Value("${email.file}")
    private String file;



    public  void send(){

        simpleMailMessage = this.getEmail();
        javaMailSender.send(simpleMailMessage);
    }

    public SimpleMailMessage getEmail(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (SimpleMailMessage) ois.readObject();
        } catch (Exception ex) {
            //TODO fixed return null
            return null;
        }
    }

    @Async
    public void sendScheduledEmail() throws Exception {
        SimpleMailMessage email = getEmail();
        long waitingTime = email.getSentDate().getTime() - (new Date()).getTime();
        if(waitingTime > 0) {
            Thread.sleep(waitingTime);
        }
        javaMailSender.send(email);

        //TODO clear Email file
    }

}
