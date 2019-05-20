package co.inventorsoft.academy.springboottask.service;

import co.inventorsoft.academy.springboottask.dao.EmailDAO;
import co.inventorsoft.academy.springboottask.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class EmailDeliveryService {
    private EmailDAO emailDAO;

    private JavaMailSender mailSender;

    @Scheduled(fixedRate  = 60000)
    public void deliverEmails(){
        new EmailDeliver().start();
    }

    @AllArgsConstructor
    private class EmailDeliver extends Thread{

        @Override
        public void run() {
            try {
                //find emails
                List<Email> emailList = emailDAO.findAllNeededToBeSent();
                for (Email email: emailList){
                    //send email
                    sendEmail(email);
                }
                //delete sent emails
                emailDAO.deleteAll(emailList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendEmail(Email email) {
            Logger log = Logger.getLogger(EmailDeliveryService.class.getName());

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email.getDeliveryAddress());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getText());
            try{
                mailSender.send(mailMessage);
            }
            catch (MailException ex){
                ex.printStackTrace();
                log.log(Level.WARNING, "Error during sending " + email);
            }
            finally {
                log.log(Level.INFO, email + " was sent successfully!");
            }
        }
    }
}
