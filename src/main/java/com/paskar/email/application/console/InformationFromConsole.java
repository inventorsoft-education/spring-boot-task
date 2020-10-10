package com.paskar.email.application.console;


import lombok.Getter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InformationFromConsole implements Serializable {

    @Getter
    private List<Email> listWithAllEmails = new ArrayList<>();

    private transient static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");
    private transient static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        InformationFromConsole console = new InformationFromConsole();

        int numberOfLetters = numberOfLettersValidation();

        for (int i = 0; i < numberOfLetters; i++) {
            console.getListWithAllEmails().add(createNewEmail());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("emailList.bin"))) {
            oos.writeObject(console.getListWithAllEmails());
        }
    }

    public static Email createNewEmail() throws IOException {
        String recipient = recipientValidation();
        String subject = emailSubject();
        String body = bodyValidation();
        LocalDateTime date = dateValidation();
        return new Email(recipient, subject, body, date);
    }


    public static String recipientValidation() throws IOException {
        String emailRegEx = "^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        System.out.println("Enter the recipient's email address \nFor example: \"YourExample@gmail.com\"");
        while (true) {
            String recipient = READER.readLine();

            Pattern p = Pattern.compile(emailRegEx);
            Matcher m = p.matcher(recipient);
            if (m.find()) {
                return recipient;
            }
            System.err.println("E-mail is not correct, try again");
        }
    }

    public static String emailSubject() throws IOException {
        System.out.println("Enter the subject of your email");
        return READER.readLine();
    }

    public static String bodyValidation() throws IOException {
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

    public static LocalDateTime dateValidation() throws IOException {
        LocalDateTime time = null;
        System.out.println("Pay attention to a date template, date has to be entered only in this format\n" +
                "month day year hours:minutes - for example - 09 12 2020 20:56\n" +
                "Enter date when it is necessary to send the letter:");
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

    public static int numberOfLettersValidation() throws IOException {
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
