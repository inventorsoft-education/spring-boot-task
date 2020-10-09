package com.paskar.email.application.sender;


import com.paskar.email.application.interafaces.InformationFromConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SimpleEmailExampleSender {

    private final JavaMailSender emailSender;

    @Autowired
    public SimpleEmailExampleSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Scheduled(fixedRate = 1000)
    public void sendSimpleEmail() {

        for (int i = 0; i < InformationFromConsole.getListWithAllEmails().size(); i++) {
            LocalDateTime localDateTime = LocalDateTime.now();
            if (localDateTime.equals(InformationFromConsole.getListWithAllEmails().get(i).getDate())) {
                SimpleMailMessage message = new SimpleMailMessage();

                message.setTo(InformationFromConsole.getListWithAllEmails().get(i).getRecipient());
                message.setSubject(InformationFromConsole.getListWithAllEmails().get(i).getSubject());
                message.setText(InformationFromConsole.getListWithAllEmails().get(i).getSubject());

                this.emailSender.send(message);

                InformationFromConsole.getListWithAllEmails().remove(i);
            }
        }

    }
}
