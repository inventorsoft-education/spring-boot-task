package com.lelek.springBoot;

import com.lelek.springBoot.service.ConsoleMessageService;
import com.lelek.springBoot.service.SenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner {

    public static final File FILE = new File("src/main/resources/data_base.json");

    private SenderService senderService;

    private ConsoleMessageService consoleMessageService;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConsoleApplication.class);
        application.run(args);
    }

    @Override
    public void run(String... args) {
        senderService.setDaemon(true);
        senderService.start();
        senderService.setStop(consoleMessageService.startConsoleMessaging());
    }
}
