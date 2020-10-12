package com.paskar.email.application;

import com.paskar.email.application.console.InformationFromConsole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.paskar.email.application.console.InformationFromConsole.*;

@SpringBootApplication
@EnableScheduling
public class EmailDeliveryApplication implements CommandLineRunner {

    private final InformationFromConsole console;

    public EmailDeliveryApplication(InformationFromConsole informationFromConsole) {
        this.console = informationFromConsole;
    }

    @Override
    public void run(String... args) throws Exception {
        int numberOfLetters = numberOfLettersValidation();

        for (int i = 0; i < numberOfLetters; i++) {
            console.getListWithAllEmails().add(createNewEmail());
        }

        save(console.getListWithAllEmails());
    }

    public static void main(String[] args) {
        SpringApplication.run(EmailDeliveryApplication.class, args);
    }
}

