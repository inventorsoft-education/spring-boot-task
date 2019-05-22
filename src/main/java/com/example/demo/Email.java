package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


@Component
public class Email implements Serializable {


    private String recipientName=null;
    private String emailSubject=null;
    private String emailBody=null;
    private Date deliveryDate=null;
    private Boolean isSent=false;


    protected void inputData(){
        
        Scanner in=new Scanner(System.in);
        System.out.println("Enter please your email recepient name");
        setRecepientName(in.nextLine());

        System.out.println("Enter please your email subject");
        setEmailSubject(in.nextLine());

        System.out.println("Enter please email body");
        setEmailBody(in.nextLine());

        System.out.println("Enter please date(dd-MM-yyyy)  on which you want to send this letter");
        String tmpDate="";
        tmpDate=in.nextLine();

        System.out.println("Enter please time(HH:mm)  on which you want to send this letter");
        String tmpTime="";
        tmpTime=in.nextLine();

        try {
            setDelieveryDate(new SimpleDateFormat("dd-MM-yyyy.HH:mm")
                    .parse(tmpDate+"."+tmpTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }




    }

    public void setRecepientName(String recepientName) {
        this.recipientName = recepientName;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public void setDelieveryDate(Date delieveryDate) {
        this.deliveryDate = delieveryDate;
    }

    public void setSended(Boolean isSended) {
        this.isSent = isSended;
    }

    public String getRecepientName() {
        return recipientName;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public Date getDelieveryDate() {
        return deliveryDate;
    }

    public Boolean getSended() {
        return isSent;
    }@Override

    public String toString() {
        return this.getEmailBody() + " " + this.getRecepientName() + " " + this.getEmailSubject() + " " + this.getDelieveryDate() + " " + this.getSended();
    }

}
