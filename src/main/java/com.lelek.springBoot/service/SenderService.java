package com.lelek.springBoot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lelek.springBoot.WebApplication;
import com.lelek.springBoot.dao.MessageDao;
import com.lelek.springBoot.model.MySimpleMailMessage;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Setter
@AllArgsConstructor
@Service
public class SenderService extends Thread {

    private static boolean stop = false;

    private JavaMailSender javaMailSender;

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
        new ObjectMapper().writeValue(WebApplication.FILE, messagesToSend);
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                log.info("5 seconds past");
                send();
                Thread.sleep(5000);
            } catch (InterruptedException | IOException e) {
                log.error("error" + e);
            }
        }
    }
}
