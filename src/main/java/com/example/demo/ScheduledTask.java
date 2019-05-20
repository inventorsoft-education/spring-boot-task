package com.example.demo;

import com.example.demo.Model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private EmailService emailService;

    public ScheduledTask(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        //logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );

        /*List<Email> list = new ArrayList<>();
        Email email1 = new Email("a", "a", "a", new Date(), false);
        Email email2 =new Email("b", "b", "b", new Date(), false);
        Email email3 =new Email("c", "c", "c", new Date(), false);
        Email email4 =new Email("d", "d", "d", new Date(), false);
        emailService.add(email1);
        emailService.add(email2);
        emailService.add(email3);
        emailService.add(email4);
        emailService.getAllEmails().forEach(System.out::println);*/
    }
}
