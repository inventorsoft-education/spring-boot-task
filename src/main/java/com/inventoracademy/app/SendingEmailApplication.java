package com.inventoracademy.app;

import com.inventoracademy.app.console.Console;
import com.inventoracademy.app.model.Mail;
import com.inventoracademy.app.repository.MailDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SendingEmailApplication implements CommandLineRunner {

    private final MailDao mailDao;
    private final Console console;

    public SendingEmailApplication(MailDao mailDao, Console console) {
        this.mailDao = mailDao;
        this.console = console;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Mail> listWithAllEmails = new ArrayList<>();
        int numberOfLetters = console.numberOfLettersValidation();

        for (int i = 0; i < numberOfLetters; i++) {
            listWithAllEmails.add(console.createNewEmailFromConsole());
        }
        mailDao.save(listWithAllEmails);
    }
    public static void main(String[] args) {
        SpringApplication.run(SendingEmailApplication.class, args);
    }
}
