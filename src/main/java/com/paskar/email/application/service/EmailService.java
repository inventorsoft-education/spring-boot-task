package com.paskar.email.application.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.paskar.email.application.console.Email;
import com.paskar.email.application.repositiory.EmailStorage;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailService {
    private final static String baseFile = "emailList.json";
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender emailSender;
    private final EmailStorage storage;
    private final ObjectMapper mapper;

    @Autowired
    public EmailService(JavaMailSender emailSender, EmailStorage storage, ObjectMapper mapper) {
        this.emailSender = emailSender;
        this.storage = storage;
        this.mapper = mapper;
    }

    @Scheduled(fixedRate = 60000) //1 min
    public void sendSimpleEmail() throws MailException, IOException {

        List<Email> emailList = storage.findAll();
        List<Email> resultList = new ArrayList<>();

        for (Email email : emailList) {
            LocalDateTime time = LocalDateTime.now();

            if (!time.equals(email.getDate())) {
                resultList.add(email);
            }

            if (time.equals(email.getDate())) {
                SimpleMailMessage message = new SimpleMailMessage();

                message.setTo(email.getRecipient());
                message.setSubject(email.getSubject());
                message.setText(email.getSubject());

                LOG.info("All information about your email {}:", email.toString());
                this.emailSender.send(message);
            }
        }
        mapper.writeValue(new File(baseFile), resultList);
    }
}
