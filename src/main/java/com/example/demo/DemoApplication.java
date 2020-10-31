package com.example.demo;

import com.example.demo.consoleInfo.Email;
import com.example.demo.consoleInfo.InfoFromUser;
import com.example.demo.repository.EmailRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

    private final EmailRepo repo;
    private final InfoFromUser info;

    public DemoApplication(EmailRepo repo, InfoFromUser info) {
        this.repo = repo;
        this.info = info;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Email> listOfEmails = new ArrayList<>();
        int numberOfEmails = info.numberOfEmailsValidation();

        for (int i = 0; i < numberOfEmails; i++) {
            listOfEmails.add(info.createNewEmail());
        }
        repo.save(listOfEmails);

    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}


