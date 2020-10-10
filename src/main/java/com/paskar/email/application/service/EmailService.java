package com.paskar.email.application.service;


import com.paskar.email.application.interafaces.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Scheduled(fixedRate = 1000)
    public void sendSimpleEmail() throws MailException, IOException, ClassNotFoundException {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("emailList.bin")))
        {
            List<Email> emailList = (List<Email>) ois.readObject();

            for (int i = 0; i < emailList.size(); i++) {
                LocalDateTime localDateTime = LocalDateTime.now();
                if (localDateTime.equals(emailList.get(i).getDate())) {
                    SimpleMailMessage message = new SimpleMailMessage();

                    message.setTo(emailList.get(i).getRecipient());
                    message.setFrom("Olexandr@gmai.com");//just an example
                    message.setSubject(emailList.get(i).getSubject());
                    message.setText(emailList.get(i).getSubject());

                    this.emailSender.send(message);

                    emailList.remove(i);
                }
            }
        }


    }
}
