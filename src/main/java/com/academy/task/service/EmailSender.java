package com.academy.task.service;

import com.academy.task.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EmailSender {

    private EmailSenderService emailSenderService;

    private EmailService emailService;

    @Autowired
    public EmailSender(EmailSenderService emailSenderService, EmailService emailService) {
        this.emailSenderService = emailSenderService;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 60000)
    public void deliverEmails() {
        List<Email> emailList = emailService.getEmailToSent();

        emailList.forEach(emailSenderService::sendEmail);
    }

}

