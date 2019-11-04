package com.lelek.springBoot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lelek.springBoot.ConsoleApplication;
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
import java.util.stream.Collectors;

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
        List<MySimpleMailMessage> messagesToSend = messageDao.getMessages().stream()
                .filter(message -> !message.isSent())
                .filter(message -> Objects.requireNonNull(message.getSentDate()).getTime() <= System.currentTimeMillis())
                .collect(Collectors.toList());
        for (MySimpleMailMessage message : messagesToSend) {
            javaMailSender.send(message);
            message.setSent(true);
        }
        new ObjectMapper().writeValue(ConsoleApplication.FILE, messagesToSend);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                send();
                Thread.sleep(5000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
