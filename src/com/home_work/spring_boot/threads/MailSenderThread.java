package com.home_work.spring_boot.threads;

import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.services.MailService;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public class MailSenderThread implements Runnable {

    private MailService mailService;

    private Letter letter;

    public MailSenderThread(MailService mailService, Letter letter) {
        this.mailService = mailService;
        this.letter = letter;
    }

    @Override
    public void run() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mailService.sendMail(letter);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, Timestamp.valueOf(letter.getDeliveryTime()));
    }
}
