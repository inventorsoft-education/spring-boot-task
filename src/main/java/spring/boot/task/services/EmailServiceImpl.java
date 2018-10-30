package spring.boot.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import spring.boot.task.EmailConsoleReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService, Runnable {
    private JavaMailSender javaMailSender;
    private EmailConsoleReader emailConsoleReader;
    private TaskScheduler taskScheduler;
    @Value("${email.filename}")
    private String fileName;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender,
                            EmailConsoleReader emailConsoleReader,
                            TaskScheduler taskScheduler)  {
        this.javaMailSender = javaMailSender;
        this.emailConsoleReader = emailConsoleReader;
        this.taskScheduler = taskScheduler;
    }
    public SimpleMailMessage getEmail() throws IOException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        if (Files.exists(Paths.get(fileName))) {
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
        if (Files.exists(Paths.get(fileName))) {
            Files.delete(Paths.get(fileName));
        }
    }
    @Override
    public void run() {
        SimpleMailMessage email = null;
        try {
            email = getEmail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        javaMailSender.send(email);
        try {
            clearEmail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    public void sendScheduledEmail(SimpleMailMessage simpleMailMessage) {
        taskScheduler.schedule(() -> run(), simpleMailMessage.getSentDate().compareTo(new Date()) <= 0 ? new Date() : simpleMailMessage.getSentDate());
    }
}
