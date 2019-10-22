package com.home_work.spring_boot.services;

import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.repository.MailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MailService {

    private JavaMailSender javaMailSender;
    private MailDao mailDao;

    @Autowired
    public MailService(JavaMailSender javaMailSender, MailDao mailDao) {
        this.mailDao = mailDao;
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Letter letter) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(letter.getRecipient());
        msg.setSubject(letter.getSubject());
        msg.setText(letter.getBody());
        msg.setSentDate(Timestamp.valueOf(letter.getDeliveryTime()));
        javaMailSender.send(msg);
    }

    public void saveMail(Letter letter) {
        mailDao.saveMail(letter);
    }

    public List<Letter> getMails() {
        return mailDao.getMails();
    }
}
