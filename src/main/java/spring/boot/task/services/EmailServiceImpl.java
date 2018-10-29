package spring.boot.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import spring.boot.task.repositories.EmailRepository;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;
    private EmailRepository emailConsoleReader;
    private String fileName = "email";

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, EmailRepository emailConsoleReader){
        this.javaMailSender = javaMailSender;
        this.emailConsoleReader = emailConsoleReader;
    }
    public SimpleMailMessage getEmail() throws IOException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        if (Files.exists(Paths.get("email"))) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                simpleMailMessage = (SimpleMailMessage) ois.readObject();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            clearEmail();
        } else {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                simpleMailMessage = emailConsoleReader.readEmail();
                oos.writeObject(simpleMailMessage);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return simpleMailMessage;
    }
    public void clearEmail() throws IOException {
        if (Files.exists(Paths.get("email"))){
            Files.delete(Paths.get("email"));
        }
    }
    @Async
    public void sendScheduledEmail() throws Exception {
        SimpleMailMessage email = getEmail();
        long timeToWaite = email.getSentDate().getTime() - (new Date()).getTime();
        if(timeToWaite > 0) {
            Thread.sleep(timeToWaite);
        }
        javaMailSender.send(email);
        clearEmail();
    }
}
