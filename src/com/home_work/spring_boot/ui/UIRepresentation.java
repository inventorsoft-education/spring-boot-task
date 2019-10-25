package com.home_work.spring_boot.ui;

import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.services.MailService;
import com.home_work.spring_boot.services.ValidationService;
import com.home_work.spring_boot.threads.MailSenderThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class UIRepresentation {

    private ValidationService validationService;
    private MailService mailService;
    private Letter letter = new Letter();
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    @Autowired
    public UIRepresentation(MailService mailService, ValidationService validationService) {
        this.validationService = validationService;
        this.mailService = mailService;
    }

    public void run() {
        checkMailsForSend();
        Scanner scanner = new Scanner(System.in);
        List<Letter> letters;
        String answer = askForSendLetter(scanner);
        try {
            while (answer.equals("yes")) {
                initLetter(letter, scanner);
                validate(letter);
                mailService.saveMail(letter);
                letters = mailService.getMailsToSend();
                answer = askForSendLetter(scanner);
                executorService.execute(new MailSenderThread(letters.get(letters.size() - 1), mailService));
            }
            executorService.shutdown();
        } catch (DateTimeParseException e) {
            System.out.println("You've entered date-time incorrect. Please, enter date-time with that template (yyyy-MM-dd HH:mm)");
            run();
        }
    }

    private void validate(Letter letter) {
        Map<String, String> errors = validationService.validate(letter);
        if (!errors.isEmpty()) {
            errors.forEach((key, value) -> {
                System.out.println("Error: " + key + ", Message: " + value);
            });
            run();
        }
    }

    private String askForSendLetter(Scanner scanner) {
        System.out.println("Do you wont to send letter?(enter \"yes\" for continue)");
        return scanner.nextLine();
    }

    private void initLetter(Letter letter, Scanner scanner) {
        System.out.println("Enter recipient");
        letter.setRecipient(scanner.nextLine());
        System.out.println("Enter subject");
        letter.setSubject(scanner.nextLine());
        System.out.println("Enter body");
        letter.setBody(scanner.nextLine());
        System.out.println("Enter delivery time(yyyy-MM-dd HH:mm)");
        letter.setDeliveryTime(parseToLocalDateTime(scanner.nextLine()));
    }

    private LocalDateTime parseToLocalDateTime(String deliveryTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(deliveryTime, formatter);
    }

    private void checkMailsForSend() {
        List<Letter> letters = mailService.getSentMails();
        for (Letter letter : letters) {
            executorService.execute(new MailSenderThread(letter, mailService));
        }
    }
}
