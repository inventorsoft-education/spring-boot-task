package com.example.lesson4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator implements IValidator {
    static private final String DATE_FORMAT = "dd-MM-yyyy HH:mm";
    @Override
    public boolean validate(String s) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try {
            dateFormat.parse(s);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
