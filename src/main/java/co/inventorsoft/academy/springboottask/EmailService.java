package co.inventorsoft.academy.springboottask;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender emailSender;
	private Logger log;
	private EmailDAO emailDAO;
	private ThreadPoolTaskScheduler scheduler;
	
	@Autowired
	public EmailService(JavaMailSender emailSender, Logger log, EmailDAO emailDAO, ThreadPoolTaskScheduler scheduler) {
		this.emailSender = emailSender;
		this.log = log;
		this.emailDAO = emailDAO;
		this.scheduler = scheduler;
	}

	public void sendFutureEmail() {
		List<SimpleMailMessage> emails = emailDAO.getAll();
		for(SimpleMailMessage email : emails) {
			log.info(" E-mail  To:{}  Subject:{}  Date:{} ",email.getTo(),email.getSubject(),email.getSentDate());
			scheduler.schedule( () -> 
				{
					try{
						log.info("\n Sending E-mail \n To: {} \n Subject: {} \n Date: {} ",email.getTo(),email.getSubject(),email.getSentDate());
						emailSender.send(email);
						emailDAO.delete(email);
						emailDAO.save();
					} catch(Exception e) {
						log.warn(" E-mail  To:{}  Subject:{}  Date:{} ",email.getTo(),email.getSubject(),email.getSentDate());
						log.error(e.getMessage());
					}
				},
				email.getSentDate() );
		}
	}

}
