package com.paskar.email.application;

import com.paskar.email.application.console.Email;
import com.paskar.email.application.repositiory.EmailRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

import static com.paskar.email.application.console.InformationFromConsole.numberOfLettersValidation;

@SpringBootApplication
@EnableScheduling
public class EmailDeliveryApplication implements CommandLineRunner {

    private final EmailRepository email;

    public EmailDeliveryApplication(EmailRepository email) {
        this.email = email;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Email> listWithAllEmails = new ArrayList<>();
        int numberOfLetters = numberOfLettersValidation();

        for (int i = 0; i < numberOfLetters; i++) {
            listWithAllEmails.add(email.createNewEmail());
        }
        email.save(listWithAllEmails);
    }

    public static void main(String[] args) {
        SpringApplication.run(EmailDeliveryApplication.class, args);
    }
}

