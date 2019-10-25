package com.lelek.springBoot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
@Setter
@Service
public class Sender extends Thread{

    private boolean stop = false;

    @Autowired
    private JavaMailSender javaMailSender;

    private List<MySimpleMailMessage> getAllMessages() throws IOException {
        if (!(new Scanner(ConsoleApplication.FILE).hasNext())) {
            return new ArrayList<>();
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            List<MySimpleMailMessage> mailMessageList;
            mailMessageList = objectMapper
                    .readValue(ConsoleApplication.FILE, new TypeReference<List<MySimpleMailMessage>>(){});
            return mailMessageList;
        }
    }

    private void send() throws IOException {
        List<MySimpleMailMessage> allMessages = new ArrayList<>(getAllMessages());
        for (MySimpleMailMessage message : allMessages){
            if (!message.isSent()){
                if(Objects.requireNonNull(message.getSentDate()).getTime() <= System.currentTimeMillis()){
                    javaMailSender.send(message);
                    message.setSent(true);
                }
            }
        }
        new ObjectMapper().writeValue(ConsoleApplication.FILE, allMessages);
    }

    @Override
    public void run() {
        while (!stop){
            try {
                send();
                Thread.sleep(2000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
