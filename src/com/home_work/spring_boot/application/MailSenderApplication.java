package com.home_work.spring_boot.application;

import com.home_work.spring_boot.command_line_execute.SendMailExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class MailSenderApplication implements CommandLineRunner {

    private SendMailExecutor sendMailExecutor;

    public MailSenderApplication(SendMailExecutor sendMailExecutor) {
        this.sendMailExecutor = sendMailExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(MailSenderApplication.class, args);
    }

    @Override
    public void run(String... args) {
        sendMailExecutor.execute();
    }
}
