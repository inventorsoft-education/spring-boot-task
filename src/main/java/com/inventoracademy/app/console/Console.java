package com.inventoracademy.app.console;

import com.inventoracademy.app.model.Mail;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Console {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public Mail createNewEmailFromConsole() throws IOException {
        String recipient = recipientValidation();
        String subject = emailSubject();
        String body = bodyValidation();
        LocalDateTime date = dateValidation();
        return new Mail(recipient, subject, body, date);
    }

    public String recipientValidation() throws IOException {
        String emailRegEx = "^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        System.out.println("Enter the recipient's email address \nFor example: \"YourExample@gmail.com\"");
        while (true) {
            String recipient = READER.readLine();

            Pattern p = Pattern.compile(emailRegEx);
            Matcher m = p.matcher(recipient);
            if (m.find()) {
                return recipient;
            }
            System.err.println("Incorrect mail, try again");
        }
    }

    public String emailSubject() throws IOException {
        System.out.println("Enter the subject of your email");
        return READER.readLine();
    }

    public String bodyValidation() throws IOException {
        StringBuilder builder = new StringBuilder();
        System.out.println("Please enter your message\n" +
                "When you have finished writing your message, please press the ENTER once and type the word \"Exit\"");
        do {
            String body = READER.readLine();
            if (body.equalsIgnoreCase("exit")) {
                return builder.toString();
            }
            builder.append(body).append(". ");
        } while (true);
    }

    public LocalDateTime dateValidation() throws IOException {
        LocalDateTime time = null;
        System.out.println("Input data to delivery this message in format mm/dd/yy hh:mm :");
        int count = 0;
        do {
            try {
                count = 0;
                time = LocalDateTime.parse(READER.readLine(), FORMATTER);
            } catch (DateTimeParseException exception) {
                count++;
                System.err.println("Pay attention to a date template, and try again");
            }
        } while (count != 0);

        return time;
    }

    public int numberOfLettersValidation() throws IOException {
        System.out.println("How many letters do you want to send? (enter a number)");
        int numberOfLetters = 0;
        int count = 0;
        do {
            try {
                count = 0;
                numberOfLetters = Integer.parseInt(READER.readLine());
            } catch (NumberFormatException e) {
                count++;
                System.err.println("You have entered an unknown character, use only digits");
            }
        } while (count != 0);
        return numberOfLetters;
    }
}
