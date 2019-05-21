package com.example.demo.repo;

import com.example.demo.exception.EmailException;
import com.example.demo.exception.IntegerException;

import java.util.Scanner;

public class InputValue {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputMessage() {
        System.out.println("Write your message :");
        String message = scanner.nextLine();
        if (message.length() < 1) {
            System.out.println("The message is very short, please enter more letters ");
            inputMessage();
        }
        return message;
    }

    public static String inputEmail() {
        System.out.println("Write email to :");
        String email = scanner.nextLine();
        if ((EmailException.isValidEmailAddress(email))) {
        } else {
            System.out.println("You are trying to enter the wrong email, please enter the email correctly ");
            inputEmail();
        }
        return email;
    }

    public static String inputSubject() {
        System.out.println("Write subject : ");
        String subject = scanner.nextLine();
        if (subject.length() < 1) {
            System.out.println("The message is very short, please enter more letters ");
            inputSubject();
        }
        return subject;
    }

    public static Integer inputSecond() {
        System.out.println("How many seconds to send  : ");
        String strSec = scanner.nextLine();
        int intSec = 0;
        if (IntegerException.tryParse(strSec)) {
            intSec = Integer.parseInt(strSec);
        } else {
            inputSecond();
        }
        return intSec;
    }

    public static boolean resend() {
        System.out.println("Do you want send new message ? 'y' 'n'");
        return scanner.nextLine().contains("y") ? true : false;
    }
}
