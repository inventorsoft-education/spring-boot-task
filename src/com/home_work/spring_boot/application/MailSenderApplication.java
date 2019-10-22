package com.home_work.spring_boot.application;

import com.home_work.spring_boot.ui.UIRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class MailSenderApplication implements CommandLineRunner {

    @Autowired
    private UIRepresentation uiRepresentation;

    public static void main(String[] args) {
        SpringApplication.run(MailSenderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        uiRepresentation.run();
    }
}
