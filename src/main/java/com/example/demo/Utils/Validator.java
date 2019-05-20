package com.example.demo.Utils;


import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {

    private final Pattern emailPattern;
    private final DateFormat dateFormat;

    public Validator() {
        emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        dateFormat = new SimpleDateFormat("dd-mm-yyyy HH:mm");
        dateFormat.setLenient(false);
    }

    public boolean isEmailValid(String email) {
        Matcher matcher = emailPattern .matcher(email);
        return matcher.find();
    }

    public boolean isDateValid(String date){
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public Date parseDate(String s){
        Date date = null;
        try {
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            //date is not valid
        }
        return date;
    }
}
