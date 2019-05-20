package com.example.demo;

import com.example.demo.Model.Email;
import com.example.demo.Service.EmailService;
import com.example.demo.Utils.EmailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTask {

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
            emailService.markAsSent(e);
        }
    }
}
