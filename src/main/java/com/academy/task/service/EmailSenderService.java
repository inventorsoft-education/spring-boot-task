package com.academy.task.service;

import com.academy.task.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private JavaMailSender mailSender;

    private EmailService emailService;

    @Autowired
    public EmailSenderService(JavaMailSender mailSender, EmailService emailService) {
        this.mailSender = mailSender;
        this.emailService = emailService;
    }

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email.getRecipient());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        this.mailSender.send(message);

        emailService.deleteEmail(email.getId());
    }

}
