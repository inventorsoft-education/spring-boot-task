package spring.boot.task;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmailConsoleReader {
    public String inputRecipientAddress() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the recipient's email address: ");
        String emailAddress = bufferedReader.readLine();
        if (!isValidEmailAddress(emailAddress)) {
            System.out.println("Please enter the recipient's email address: ");
            emailAddress = bufferedReader.readLine();
        }
        return emailAddress;
    }
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    public String inputSubject() throws IOException {
        BufferedReader bufferedReaderSubject = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the subject of the email: ");
        String subject = bufferedReaderSubject.readLine();
        if (subject == null || subject.isEmpty()) {
            System.out.println("Please enter the subject of the email: ");
            subject = bufferedReaderSubject.readLine();
        }
        return subject;
    }
    public String inputText() throws IOException {

        BufferedReader bufferedReaderText = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the email text: ");
        String text = bufferedReaderText.readLine();
        if (text == null || text.isEmpty()) {
            System.out.println("Please enter the email text: ");
            text = bufferedReaderText.readLine();
        }
        return text;
    }
    private boolean isDateValid(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    public Date inputDate() throws IOException {

        BufferedReader bufferedReaderDate = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the date of sending: ");
        String dateString = bufferedReaderDate.readLine();
        while ( ! isDateValid(dateString) ) {
            System.out.println(" Date is invalid. Please enter in format: Day.Month.Year Hours:minutes");
            dateString = bufferedReaderDate.readLine();
        }
        try {
            return (new SimpleDateFormat("dd.MM.yy HH:mm")).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
    public SimpleMailMessage readFromConsole() throws IOException {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(inputRecipientAddress());
        email.setSubject(inputSubject());
        email.setText(inputText());
        email.setSentDate(inputDate());
        return email;
    }
}
