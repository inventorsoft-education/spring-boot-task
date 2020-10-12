package com.paskar.email.application.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paskar.email.application.console.Email;
import com.paskar.email.application.repositiory.FindEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class EmailService implements FindEmail {
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    private final static String baseFile = "emailList.json";

    @Override
    public List<Email> findAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), new TypeReference<List<Email>>() {
        });
    }

    @Override
    public Optional<Email> findEmailsNearDeliveryDate(LocalDateTime currentDate) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Email> emailList = findAll();
        for (int i = 0; i < emailList.size() ; i++) {
            if (time.equals(emailList.get(i).getDate())) {
                return Optional.of(emailList.get(i));
            }
        }
        return Optional.empty();
    }

    @Scheduled(fixedRate = 60000) //1 min
    public void sendSimpleEmail() throws MailException, IOException {

        List<Email> emailList = findAll();

        for (Email email : emailList) {
            LocalDateTime time = LocalDateTime.now();

            if (time.equals(email.getDate())) {
                SimpleMailMessage message = new SimpleMailMessage();

                message.setTo(email.getRecipient());
                message.setSubject(email.getSubject());
                message.setText(email.getSubject());

                LOG.info("All information about your email {}:", email.toString());
                this.emailSender.send(message);

            }
        }
    }
}
