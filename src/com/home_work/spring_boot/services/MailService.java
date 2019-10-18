package com.home_work.spring_boot.services;

import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.repository.MailDao;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class MailService {

    private JavaMailSender javaMailSender;
    private MailDao mailDao;

    public MailService(JavaMailSender javaMailSender, MailDao mailDao) {
        this.mailDao = mailDao;
        this.javaMailSender = javaMailSender;
    }

    public void sendMail() {
        Letter letter = mailDao.getLetter();
        if (letter.getDeliveryTime().compareTo(LocalDateTime.now()) < 0){
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(letter.getRecipient());
            msg.setSubject(letter.getSubject());
            msg.setText(letter.getBody());
            msg.setSentDate(Timestamp.valueOf(letter.getDeliveryTime()));
            javaMailSender.send(msg);
        }
    }
    public void storeMail(Letter letter){
        try {
            mailDao.saveMail(letter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Letter getMail(){
       return mailDao.getLetter();
    }
}
