package com.paskar.email.application;

import com.paskar.email.application.console.Email;
import com.paskar.email.application.console.InformationFromConsole;
import com.paskar.email.application.repositiory.EmailRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class EmailDeliveryApplication implements CommandLineRunner {

    private final EmailRepository repository;
    private final InformationFromConsole console;

    public EmailDeliveryApplication(EmailRepository email, InformationFromConsole console) {
        this.repository = email;
        this.console = console;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Email> listWithAllEmails = new ArrayList<>();
        int numberOfLetters = console.numberOfLettersValidation();

        for (int i = 0; i < numberOfLetters; i++) {
            listWithAllEmails.add(console.createNewEmailFromConsole());
        }
        repository.save(listWithAllEmails);
    }

    public static void main(String[] args) {
        SpringApplication.run(EmailDeliveryApplication.class, args);
    }
}

