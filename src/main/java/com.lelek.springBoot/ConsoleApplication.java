package com.lelek.springBoot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner {

    static final File FILE = new File("src/main/resources/data_base.json");
    private static final String NEW_MESSAGE = "N";
    private static final String QUITE = "Q";
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Autowired
    private MySimpleMailMessage simpleMailMessage;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ConsoleApplication.class);
        application.run(args);
    }

    @Override
    public void run(String... args) {
        sender.setDaemon(true);
        sender.start();
        startConsoleMessaging();
    }

    private void restart(String input) throws IOException{
        if (NEW_MESSAGE.equals(input)) {
            startConsoleMessaging();
        } else if (QUITE.equals(input)) {
            sender.setStop(true);
        } else {
            System.out.print("Enter \"N\" if You want add new message,  enter \"Q\" if You want shut down app: ");
            restart(br.readLine());
        }
    }

    private void startConsoleMessaging() {
        try {
            System.out.print("Enter email: ");
            simpleMailMessage.setTo(br.readLine());
            simpleMailMessage.setCc(simpleMailMessage.getTo());
            simpleMailMessage.setBcc(simpleMailMessage.getTo());
            System.out.print("Enter subject: ");
            simpleMailMessage.setSubject(br.readLine());
            System.out.print("Enter message: ");
            simpleMailMessage.setText(br.readLine());
            simpleMailMessage.setId(MySimpleMailMessage.generateId());
            simpleMailMessage.setSent(false);
            System.out.print("Enter delivery date and time (dd.MM.yyyy HH:mm): ");
            simpleMailMessage.setSentDate(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(br.readLine()));
            simpleMailMessage.save();
            System.out.print("Enter \"N\" if You want add new message,  enter \"Q\" if You want shut down app: ");
            restart(br.readLine());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
