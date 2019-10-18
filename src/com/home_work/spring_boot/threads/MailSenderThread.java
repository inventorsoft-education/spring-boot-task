package com.home_work.spring_boot.threads;

import com.home_work.spring_boot.services.MailService;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class MailSenderThread extends Thread {

    private MailService mailService;

    public MailSenderThread(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void run() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mailService.sendMail();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, Timestamp.valueOf(mailService.getMail().getDeliveryTime()));
    }
}
