package com.example.lesson4;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class EmailController {

    static EmailValidator emailValidator;

    @Autowired
    public EmailController(EmailValidator emailValidator1){
        emailValidator = emailValidator1;
    }
    static public String emailMaker(Scanner scanner) {
        String email = scanner.nextLine();

        while ( ! emailValidator.validate(email) ) {
            System.out.println("Please, be carefully! Email is invalid : ");
            email = scanner.nextLine();
        }
        return email;
    }
}
