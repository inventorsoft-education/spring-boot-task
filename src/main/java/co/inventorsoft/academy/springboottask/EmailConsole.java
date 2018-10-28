package co.inventorsoft.academy.springboottask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailConsole implements EmailDAO {

	static private String emailRegexp;
	static private String dateFormat;

	@Value("${email.regexp}")
	private void setEmailRegexp(String emailRegexp) {
		EmailConsole.emailRegexp = emailRegexp;
	}

	@Value("${date.format}")
	private void setDateFormat(String dateFormat) {
		EmailConsole.dateFormat = dateFormat;
	}

	static private boolean isEmailValid(String emailAddr) {
		return Pattern.compile(emailRegexp, Pattern.CASE_INSENSITIVE).matcher(emailAddr).find();
	}

	static private String inputEmail(Scanner scanner) {
		String emailAddr = scanner.nextLine();
		while ( ! isEmailValid(emailAddr) ) {
			System.out.println(" Email is invalid. Please input again : ");
			emailAddr = scanner.nextLine();			
		}
		return emailAddr;
	}	

	static private String inputText (Scanner scanner) { 
		StringBuilder input = new StringBuilder("");
		String line;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.isEmpty()) {
				break;
			}
			input.append(line).append("\n");
		}
		return input.toString();
	}
	
	static private boolean isDateValid(String dateString) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		df.setLenient(false);
		try {
			df.parse(dateString);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	static private Date inputDate(Scanner scanner) {
		String dateString = scanner.nextLine();
		while ( ! isDateValid(dateString) ) {
			System.out.printf(" Date is invalid. Please input again in format %s : \n", dateFormat);
			dateString = scanner.nextLine();			
		}
		try {
			return (new SimpleDateFormat(dateFormat)).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}	

	static public SimpleMailMessage readFromConsole() {
		SimpleMailMessage email = new SimpleMailMessage();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter recipient's email address : ");
		email.setTo( inputEmail(scanner) ); 
		System.out.println("Please enter email subject : ");
		email.setSubject( scanner.nextLine() ); 
	    System.out.println("Please enter email text : ");
		email.setText( inputText(scanner) );
		System.out.printf("Please enter date to send in format %s : \n", dateFormat);
		email.setSentDate( inputDate(scanner) ); 
		scanner.close();
		return email;
	}
	
	@Override
	public SimpleMailMessage getEmail() {
		return readFromConsole();
	}

	@Override
	public void saveEmail() {
	}

	@Override
	public void clearEmail() {
	}

}
