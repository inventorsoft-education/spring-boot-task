package com.example.lesson4;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateController {

    static DateValidator dateValidator;
    static private final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

    @Autowired
    public DateController(DateValidator dateValidator1){
        dateValidator = dateValidator1;
    }

    public static Date dateMaker(Scanner scanner){

        String dateString = scanner.nextLine();

        while (! dateValidator.validate(dateString)){
            System.out.println("Please, be carefully! DATE format is 'dd-MM-yyyy HH:mm' :");
            dateString = scanner.nextLine();
        }

        try {
            return (new SimpleDateFormat(DATE_FORMAT)).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
