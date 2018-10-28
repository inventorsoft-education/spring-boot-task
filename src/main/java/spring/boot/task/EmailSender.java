package spring.boot.task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.Date;

@Service
public class EmailSender{
    private JavaMailSender emailSender;
    private EmailService emailService;
    @Autowired
    public EmailSender(JavaMailSender emailSender, EmailService emailService) {
        this.emailSender = emailSender;
        this.emailService = emailService;
    }
    @Async
    public void sendScheduledEmail() throws IOException, InterruptedException {
        SimpleMailMessage email = emailService.getEmail();
        long timeToWaite = email.getSentDate().getTime() - (new Date()).getTime();
        if(timeToWaite > 0) {
            Thread.sleep(timeToWaite);
        }
        emailSender.send(email);
        emailService.clearEmail();
    }
}
