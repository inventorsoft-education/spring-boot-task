package com.lelek.springBoot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lelek.springBoot.WebApplication;
import com.lelek.springBoot.dao.MessageDao;
import com.lelek.springBoot.model.MySimpleMailMessage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Setter
@Service
public class SenderService extends Thread {

    private boolean stop = false;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageDao messageDao;

    private void send() throws IOException {
        List<MySimpleMailMessage> allMessages = messageDao.getMessages();
        for (MySimpleMailMessage message : allMessages) {
            if (!message.isSent()) {
                if (Objects.requireNonNull(message.getSentDate()).getTime() <= System.currentTimeMillis()) {
                    javaMailSender.send(message);
                    message.setSent(true);
                }
            }
        }
        new ObjectMapper().writeValue(WebApplication.FILE, allMessages);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                log.info("5 seconds past");
                send();
                Thread.sleep(5000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
