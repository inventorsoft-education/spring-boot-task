package spring.boot.task.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    SimpleMailMessage getEmail() throws Exception;
    void clearEmail() throws Exception;
    void sendScheduledEmail(SimpleMailMessage simpleMailMessage)throws Exception;
}
