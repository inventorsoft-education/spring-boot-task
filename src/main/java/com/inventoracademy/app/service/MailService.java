package com.inventoracademy.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoracademy.app.model.Mail;
import com.inventoracademy.app.repository.MailDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender emailSender;
    private final MailDaoImpl mailDao;
    private final ObjectMapper mapper;

    public MailService(JavaMailSender emailSender, MailDaoImpl mailDao, ObjectMapper mapper) {
        this.emailSender = emailSender;
        this.mailDao = mailDao;
        this.mapper = mapper;
    }


    @Scheduled(fixedRate = 60000) //1 min
    public void sendSimpleEmail() throws MailException, IOException {
        List<Mail> emailsNearDeliveryDate = mailDao.findEmailsNearDeliveryDate();
        for (Mail emails : emailsNearDeliveryDate) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(emails.getEmail());
            message.setSubject(emails.getSubject());
            message.setText(emails.getSubject());

            LOG.info("All information about your email {}:", emails.toString());
            this.emailSender.send(message);
        }
        mailDao.deletingMassagesThatWereSent();
    }
}
