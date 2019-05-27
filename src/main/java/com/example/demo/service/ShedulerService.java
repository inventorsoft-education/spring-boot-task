package com.example.demo.service;

import com.example.demo.exception.DateValidation;
import com.example.demo.model.Message;
import com.example.demo.model.Status;
import com.example.demo.repo.JsonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@EnableScheduling
public class ShedulerService {

    private JsonRepo jsonRepo;
    private Set<Message> messageListDoNotSend;
    private List<Message> messageLists;
    private MailSenderService messageService;

    @Autowired
    public ShedulerService(JsonRepo jsonRepo, MailSenderService messageService) {
        this.messageService = messageService;
        this.jsonRepo = jsonRepo;
        this.messageListDoNotSend = new LinkedHashSet<>();
    }

    @Scheduled(fixedDelay = 1000)
    private void dataCheck() {
        messageLists = jsonRepo.loadMessagesDoNotSent();

        if (messageLists.size() != 0) {
            sendMessageInFuture();
        }
    }

    private void sendMessageInFuture() {
        for (Message message : messageLists) {
            if (DateValidation.dateTransformer(message.getFutureSecond(), message.getCurrentTime())) {
                jsonRepo.changeStatusById((int) message.getId(), message);
                messageService.send(message);
            }
        }
    }
}
