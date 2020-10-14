package co.inventorsoft.springmail.service;

import co.inventorsoft.springmail.dao.EmailDAO;
import co.inventorsoft.springmail.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class Delivery {

    private EmailDAO emailDAO;

    private JavaMailSender mailSender;

    @Scheduled(fixedRate  = 60000) //1 min
    public void deliverEmails(){
        new EmailDeliver().start();
    }

    @AllArgsConstructor
    private class EmailDeliver extends Thread{

        @Override
        public void run() {
            try {
                List<Email> emailList = emailDAO.emailsToSend();
                for (Email email: emailList){
                    sendEmail(email);
                }
                emailDAO.deleteAll(emailList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendEmail(Email email) {
            Logger log = Logger.getLogger(Delivery.class.getName());

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email.getRecipient());
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
