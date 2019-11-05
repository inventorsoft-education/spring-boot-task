package com.home_work.spring_boot.ui;

import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.services.MailService;
import com.home_work.spring_boot.services.ValidationService;
import com.home_work.spring_boot.threads.MailSenderThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

@Component
public class UIRepresentation {

    private ValidationService validationService;
    private MailService mailService;
    private Letter letter;
    ExecutorService executorService;


    @Autowired
    public UIRepresentation(MailService mailService, ValidationService validationService, ExecutorService executorService) {
        this.validationService = validationService;
        this.mailService = mailService;
        this.executorService = executorService;
        this.letter = new Letter();
    }

    public Letter createLetter() {
        mailService.checkMailsForSend();
        Scanner scanner = new Scanner(System.in);
        try {
            initLetter(letter, scanner);
            validate(letter);
            mailService.saveMail(letter);
            return letter;
        } catch (DateTimeParseException e) {
            System.out.println("You've entered date-time incorrect. Please, enter date-time with that template (yyyy-MM-dd HH:mm)");
            return letter;
        }
    }

    private void validate(Letter letter) {
        Map<String, String> errors = validationService.validate(letter);
        if (!errors.isEmpty()) {
            errors.forEach((key, value) ->
                    System.out.println("Error: " + key + ", Message: " + value));
            createLetter();
        }
    }

    public String askForSendLetter(Scanner scanner) {
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

}
