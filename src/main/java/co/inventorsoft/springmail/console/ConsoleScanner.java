package co.inventorsoft.springmail.console;

import co.inventorsoft.springmail.dao.EmailDAO;
import co.inventorsoft.springmail.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ConsoleScanner implements CommandLineRunner {
    private EmailDAO emailDAO;

    @Autowired
    public ConsoleScanner(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        inputScan();
    }

    private void inputScan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 1 to write an email or press 0 to exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                buildMail();
                inputScan();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("You should write 1 or 0. Please try again");
        }
    }

    private void buildMail() {
        Scanner scanner = new Scanner(System.in);
        String recipient = "";
        String subject = "";
        String body = "";
        String stringDate = "";

        System.out.println("Recipient data: ");
        recipient = scanner.nextLine();
        if (!mailCheck(recipient)){
            System.out.println("Invalid email address!");
            return;
        }

        System.out.println("Subject: ");
        subject =  scanner.nextLine();

        System.out.println("Body: ");
        body = scanner.nextLine();

        System.out.println("Delivery Date in format (dd.MM.yyyy HH:mm): ");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        stringDate = scanner.nextLine();
        try {
            LocalDateTime date = LocalDateTime.parse(stringDate);
            Email email = new Email(recipient, subject, body, date);
            emailDAO.saveMail(email);
            System.out.println("Email was saved!");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            System.out.println("Invalid delivery date!");
        }

    }

    private boolean mailCheck(String recipient){
        Pattern pattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");
        Matcher matcher = pattern.matcher(recipient);
        return matcher.matches();
    }
}
