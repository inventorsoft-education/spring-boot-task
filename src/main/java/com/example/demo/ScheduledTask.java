package com.example.demo;

import com.example.demo.Model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private EmailService emailService;
    private EmailSender emailSender;

    public ScheduledTask(EmailService emailService, EmailSender emailSender) {
        this.emailService = emailService;
        this.emailSender = emailSender;
    }

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        List<Email> emails = emailService.getEmailsToSend();
        for (Email e:emails){
            Boolean isSend = emailSender.sendEmail(e);
            e.setSend(isSend);
            emailService.setSend(e);
        }
    }
}
