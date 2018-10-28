package co.inventorsoft.academy.springboottask;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

@Repository
public class EmailFile implements EmailDAO {
	
	@Autowired
	private SimpleMailMessage email;
	
	@Value("${email.file}")
	private String file;
	
	@Override
	public SimpleMailMessage getEmail() {
		SimpleMailMessage mailConsole;
		if(Files.exists(Paths.get(file))) {
			try(FileInputStream fis = new FileInputStream(file); 
				ObjectInputStream ois = new ObjectInputStream(fis)) {
						email = (SimpleMailMessage) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				mailConsole = EmailConsole.readFromConsole();
				if(mailConsole != null) {
					email = mailConsole;
				}
			}
		} else {
			mailConsole = EmailConsole.readFromConsole();
			if(mailConsole != null) {
				email = mailConsole;
			}
		}
		return email;
	}

	@Override
	public void saveEmail() {
		clearEmail();
		try(FileOutputStream fos = new FileOutputStream(file); 
			ObjectOutputStream oos = new ObjectOutputStream(fos)) {
					oos.writeObject(email);
		} catch (IOException e) {
			e.printStackTrace();
			clearEmail();
		}
	}

	@Override
	public void clearEmail() {
		try {
			Files.deleteIfExists(Paths.get(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
