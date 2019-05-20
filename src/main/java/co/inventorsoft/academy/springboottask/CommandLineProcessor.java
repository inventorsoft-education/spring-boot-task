package co.inventorsoft.academy.springboottask;

import co.inventorsoft.academy.springboottask.dao.EmailDAO;
import co.inventorsoft.academy.springboottask.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandLineProcessor implements CommandLineRunner {
    private EmailDAO emailDAO;

    @Autowired
    public CommandLineProcessor(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        inputProcessing();
    }

    public void inputProcessing() throws IOException {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("\nChoose option: 1 - Write an email, 0 - Exit.");
        //System.out.print("Option -> ");
        int option = consoleScanner.nextInt();
        if (option == 1){
            writeEmail();
            inputProcessing();
        }
        else if (option == 0){
            System.exit(0);
        }
        else {
            System.out.print("Incorrect input value. Try Again. Option -> ");
        }
    }

    private void writeEmail() throws IOException {
        Scanner consoleScanner = new Scanner(System.in);
        String address = "", subject = "", text = "", stringDate = "";
        System.out.println("**************New Email**************");

        System.out.println("Delivery Address: ");
        //read inputted address
        address = consoleScanner.nextLine();
        if (!isAddressValid(address)){
            System.out.println("Invalid email address!");
            return;
        }

        System.out.println("Subject: ");
        //read inputted subject
        subject =  consoleScanner.nextLine();

        System.out.println("Text: ");
        //read inputted text
        text = consoleScanner.nextLine();

        System.out.println("Delivery Date (dd.MM.yyyy HH:mm): ");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        //read inputted date
        stringDate = consoleScanner.nextLine();
        Date deliveryDate = null;
        try {
            //Convert date from string to object "Date"
            deliveryDate = dateFormat.parse(stringDate);
            Email email = new Email(address,subject,text,deliveryDate);

            //save email to file
            emailDAO.save(email);
            System.out.println("Email was saved!");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Invalid delivery date!");
        }
    }

    //Email address validation
    private boolean isAddressValid(String address){
        Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");
        Matcher matcher = emailPattern.matcher(address);
        return matcher.matches();
    }
}
