package com.example.demo.service;

import com.example.demo.consoleInfo.Email;
import com.example.demo.repository.SavedEmails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;



@Component
public class EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender emailSender;
    private final SavedEmails savedEmails;
    public final ObjectMapper mapper;

    @Autowired
    public EmailService(JavaMailSender emailSender, SavedEmails savedEmails,ObjectMapper mapper) {
        this.emailSender = emailSender;
        this.savedEmails = savedEmails;
        this.mapper= mapper;

    }

    @Scheduled(fixedRate = 60000) //1 min
    public void sendSimpleEmail() throws MailException, IOException {
        List<Email> emailsForSending = savedEmails.showEmailsForSending();
        for (Email emails : emailsForSending) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(emails.getRecipient());
            message.setSubject(emails.getSubject());
            message.setText(emails.getSubject());

            LOG.info("All information about your email {}:", emails.toString());
            this.emailSender.send(message);
        }

        savedEmails.deleteSentEmails();
    }
}


