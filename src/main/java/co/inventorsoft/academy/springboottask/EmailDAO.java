package co.inventorsoft.academy.springboottask;

import org.springframework.mail.SimpleMailMessage;

public interface EmailDAO {
	
	SimpleMailMessage getEmail();
	void saveEmail();
	void clearEmail();

}
