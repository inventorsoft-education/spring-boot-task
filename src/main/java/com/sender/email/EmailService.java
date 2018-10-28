package com.sender.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Scanner;

@Service
public class EmailService {
    private Email email;

    private TaskScheduler taskScheduler;

    private JavaMailSender javaMailSender;

    private SimpleMailMessage simpleMailMessage;


    public class EmailSending implements Runnable {
        @Override
        public void run() {
            simpleMailMessage.setTo(getEmail().getRecipient());
            simpleMailMessage.setSubject(getEmail().getSubject());
            simpleMailMessage.setText(getEmail().getBody());
            javaMailSender.send(simpleMailMessage);
            getEmail().setSentStatus(true);
            getEmail().loadIntoFile();
            System.out.println("Email has been sent successfully!");
            System.exit(0);
        }
    }

    @PostConstruct
    public void postInit() {
        getEmail().uploadFromFile();
        if (!getEmail().isSent()) {
            System.out.println("You have unsent email:\n To: " + getEmail().getRecipient() + "\nSubject: " + getEmail().getSubject() + "\n Body: " + getEmail().getBody() + "\nWould you like to resend(y/n) it?");
            char answer = ' ';
            answer = new Scanner(System.in).nextLine().charAt(0);
            if(answer == 'y') {
                getEmail().setSentStatus(true);
                getEmail().loadIntoFile();
                send();
            }

        }
    }

    public void send() {
        try {
            taskScheduler.schedule(
                    new EmailSending(),
                    getEmail().getDeliveryDate().compareTo(new Date()) <= 0 ? new Date() : getEmail().getDeliveryDate()
            );

        } catch (Exception e) {
            System.out.println("Cannot send email: " + e);
        }
    }

    @Autowired
    EmailService(TaskScheduler taskScheduler, JavaMailSender javaMailSender, Email email, SimpleMailMessage simpleMailMessage) {
        this.setEmail(email);
        this.taskScheduler = taskScheduler;
        this.javaMailSender = javaMailSender;
        this.simpleMailMessage = simpleMailMessage;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

}
