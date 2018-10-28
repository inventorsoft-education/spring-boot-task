package co.inventorsoft.academy.springboottask;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender emailSender;
	private Logger log;
	private EmailDAO emailDAO;
	
	@Autowired
	public EmailService(JavaMailSender emailSender, Logger log, @Qualifier("emailFile") EmailDAO emailDAO) {
		this.emailSender = emailSender;
		this.log = log;
		this.emailDAO = emailDAO;
	}

	@Async
	public void sendFutureEmail() {
		SimpleMailMessage email = emailDAO.getEmail();
		emailDAO.saveEmail();
		try{
			long milliesToWait = email.getSentDate().getTime() - (new Date()).getTime();
			if(milliesToWait > 0) {
				log.warn(" Waiting... {} ", email.getSentDate());
				Thread.sleep(milliesToWait);
			}
			log.info("\n E-mail \n To: {} \n Subject: {} \n Date: {} ", email.getTo(), email.getSubject(), email.getSentDate());
			emailSender.send(email);
			emailDAO.clearEmail();
		}
		catch(Exception e) {
			log.warn(" E-mail was not sent ");
			log.error(e.getMessage());
		}
	}

}
