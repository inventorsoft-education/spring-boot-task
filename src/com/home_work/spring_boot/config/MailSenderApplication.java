package com.home_work.spring_boot.config;

import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.services.MailService;
import com.home_work.spring_boot.threads.MailSenderThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan("com")
public class MailSenderApplication implements CommandLineRunner {

    @Autowired
    private MailService mailService;

    @Autowired
    private MailSenderThread mailSenderThread;

    public static void main(String[] args) {
        SpringApplication.run(MailSenderApplication.class, args);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setUsername("chorneivasul@gmail.com");
        mailSender.setPassword("rhitbecyujdikjoz");
        mailSender.setPort(587);
        mailSender.setHost("smtp.gmail.com");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Override
    public void run(String... args) throws Exception {
        Letter letter = new Letter();
        createLetter(letter);
        mailService.storeMail(letter);
        mailSenderThread.run();
    }

    private void createLetter(Letter letter) {
        System.out.println("Enter recipient");
        Scanner scanner = new Scanner(System.in);
        letter.setRecipient(scanner.nextLine());
        System.out.println("Enter subject");
        letter.setSubject(scanner.nextLine());
        System.out.println("Enter body");
        letter.setBody(scanner.nextLine());
        letter.setDeliveryTime(LocalDateTime.now());
    }
}
