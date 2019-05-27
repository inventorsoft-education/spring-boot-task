package com.example.demo.exception;

import java.util.Date;

public class DateValidation {

    public static boolean dateTransformer(long date, Date jdate) {
        Date current = new Date();
        System.out.println(jdate.getTime() + (date * 1000) <= current.getTime());
        return jdate.getTime() + (date * 1000) <= current.getTime() ? true : false;
    }
}
