package com.example.lesson4;

import java.util.regex.Pattern;

public class EmailValidator implements IValidator {

    static private final String EMAIL_REGEXP = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    @Override
    public boolean validate(String s) {
        return Pattern.compile(EMAIL_REGEXP, Pattern.CASE_INSENSITIVE).matcher(s).find();
    }
}
