package com.sender.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Component
public class Email implements EmailDataAccess, Serializable {
    private String recipient;

    private String subject;

    private String body;

    private Date deliveryDate;

    private Boolean isSent;


    @Autowired
    Email(@Value("${mail.recipient}") String recipient,
          @Value("${mail.subject}") String subject,
          @Value("${mail.body}") String body,
          @Value("#{new java.util.Date()}") Date deliveryDate,
          @Value("${mail.isSent}") String isSent) {
        this.setRecipient(recipient);
        this.setSubject(subject);
        this.setBody(body);
        this.setDeliveryDate(deliveryDate);
        this.isSent = Boolean.parseBoolean(isSent);
    }


    @Override
    public String toString() {
        return this.getBody() + " " + this.getRecipient() + " " + this.getSubject() + " " + this.getDeliveryDate() + " " + this.isSent();
    }

    public void inputData() throws ParseException, AddressException {
        Scanner reader = new Scanner(System.in);
        String recipient = " ";
        String subject = " ";
        String body = " ";
        Date deliveryDate = new Date(System.currentTimeMillis() + 10000);

        System.out.println("Enter recipient of email: ");
        recipient = reader.nextLine();
        InternetAddress email = new InternetAddress(recipient);
        email.validate();

        System.out.println("Enter subject of email: ");
        subject = reader.nextLine();


        System.out.println("Enter body of email: ");
        body = reader.nextLine();

        System.out.println("Enter date (dd-MM-yyyy) on which u want to send the email: ");
        String date = "";
        date = reader.nextLine();

        System.out.println("Enter time (hh:mm) at which u want to send the email : ");
        String time = "";
        time = reader.nextLine();

        deliveryDate = new SimpleDateFormat("dd-MM-yyyy.hh:mm").parse(date + "." + time);

        this.setRecipient(recipient);
        this.setSubject(subject);
        this.setBody(body);
        this.setDeliveryDate(deliveryDate);

        loadIntoFile();
    }

    @Override
    public void loadIntoFile() {
        try {
            FileOutputStream fileStream = new FileOutputStream("./cache.cer");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(this);

            fileStream.close();
            objectStream.close();
        } catch (Exception e){
            System.out.println("Writing into file error: "  + e);
        }
    }

    @Override
    public void uploadFromFile() {
        try {
            FileInputStream fileStream = new FileInputStream("./cache.cer");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            Email data = (Email)objectStream.readObject();

            this.setRecipient(data.recipient);
            this.setSubject(data.subject);
            this.setBody(data.body);
            this.setDeliveryDate(data.deliveryDate);
            this.isSent = data.isSent;

            fileStream.close();
            objectStream.close();
        } catch (Exception e) {
            System.out.println("Reading from file error: " + e);
        }
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean isSent() {
        return isSent;
    }

    public void setSentStatus(Boolean sent) {
        isSent = sent;
    }

}
