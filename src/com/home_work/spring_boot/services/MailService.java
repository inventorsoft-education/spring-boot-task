package com.home_work.spring_boot.services;

import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.repository.MailDao;
import com.home_work.spring_boot.threads.MailSenderThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class MailService {

    private JavaMailSender javaMailSender;
    private ExecutorService executorService;
    private MailDao mailDao;
    private static final String MAILS_TO_SEND = "fileNameToSend";
    private static final String SENT_MAILS = "fileNameOfSent";


    @Autowired
    public MailService(JavaMailSender javaMailSender, MailDao mailDao, ExecutorService executorService) {
        this.mailDao = mailDao;
        this.javaMailSender = javaMailSender;
        this.executorService = executorService;
    }

    public void sendMail(Letter letter) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(letter.getRecipient());
        msg.setSubject(letter.getSubject());
        msg.setText(letter.getBody());
        msg.setSentDate(Timestamp.valueOf(letter.getDeliveryTime()));
        javaMailSender.send(msg);
        mailDao.removeSentMail(letter);
    }

    public void saveMail(Letter letter) {
        mailDao.saveMail(letter, MAILS_TO_SEND);
    }

    public List<Letter> getMailsToSend() {
        return mailDao.getMails(MAILS_TO_SEND);
    }

    private List<Letter> getSentMails() {
        return mailDao.getMails(SENT_MAILS);
    }

    public void checkMailsForSend() {
        List<Letter> mails = getSentMails();
        for (Letter mail : mails) {
            executorService.execute(new MailSenderThread(this, mail));
        }
    }

}
