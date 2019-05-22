package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@EnableScheduling
public class EmailService {

    Email email;
    SimpleMailMessage simpleMailMessage;
    JavaMailSender javaMailSender;
    ArrayList<Email> allEmailsList;
    WorkWithJsonFile jsonFileWork;
    ArrayList<Email> current;

    @Autowired
    public EmailService(WorkWithJsonFile jsonFile,JavaMailSender javaMailSender) {
        email=new Email();
        this.jsonFileWork=jsonFile;
        allEmailsList=jsonFileWork.loadFromJsonEmailsList();
        this.javaMailSender=javaMailSender;
        current=new ArrayList<Email>();
        simpleMailMessage=new SimpleMailMessage();
    }

    @Scheduled(fixedRate = 20000)
    public void lookingForCurrentEmeils() {

        //magic part of code with smell
        Date finallyDate=null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String s1=dateFormat.format(date);
        DateFormat dateFormatHours = new SimpleDateFormat("HH:mm");
        Date hours=new Date();
        String hoursS=dateFormatHours.format(hours);

        try {
             finallyDate=new SimpleDateFormat("dd-MM-yyyy.HH:mm")
                    .parse(new String(s1)+"."+new String(hoursS));
        } catch (ParseException e) {
            e.printStackTrace();
        };


        for (Email counter:allEmailsList){
            if (counter.getDelieveryDate().compareTo(finallyDate)==0 && !counter.getSended() && !allEmailsList.contains(counter)){
                current.add(counter);
            }
        }

    }

    @Scheduled(fixedRate = 60000)
    public void amount(){
       if (current.size()!=0){
           for (Email counter:current){
               simpleMailMessage.setTo(counter.getRecepientName());
               simpleMailMessage.setText(counter.getEmailBody());
               simpleMailMessage.setSubject(counter.getEmailSubject());
               javaMailSender.send(simpleMailMessage);
           }
       }
    }






}
