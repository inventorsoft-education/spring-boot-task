package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

@Service
public class InputEmailService {

    private Email email=new Email();
    private WorkWithJsonFile workWithJsonFile;

    @Autowired
    public InputEmailService(WorkWithJsonFile workWithJsonFile) {
        this.workWithJsonFile = workWithJsonFile;
    }

    @Scheduled(fixedDelay = 10000)
    private void userInterfaceView(){
        String flag = " ";


        do {
            System.out.println("Would you like to add new message?" + "y/n");
            Scanner in = new Scanner(System.in);
            flag = in.nextLine();

            if (flag.equals("n")){
                break;
            }else if (flag.equals("y")){
                inputDataAboutEmail();
                saveToJsonFile();
            } else{
                System.out.println("Wrong input parameter try again");
            }

        }
        while(!flag.equals("n"));
    }


    private void inputDataAboutEmail(){

        Scanner in=new Scanner(System.in);
        System.out.println("Enter please your email recepient name");
        email.setRecepientName(in.nextLine());

        System.out.println("Enter please your email subject");
        email.setEmailSubject(in.nextLine());

        System.out.println("Enter please email body");
        email.setEmailBody(in.nextLine());

        System.out.println("Enter please date(dd-MM-yyyy)  on which you want to send this letter");
        String tmpDate="";
        tmpDate=in.nextLine();

        System.out.println("Enter please time(HH:mm)  on which you want to send this letter");
        String tmpTime="";
        tmpTime=in.nextLine();

        try {
            email.setDeliveryDate(new SimpleDateFormat("dd-MM-yyyy.HH:mm")
                    .parse(tmpDate+"."+tmpTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void saveToJsonFile(){
        workWithJsonFile.saveEmailToJson(email);
    }


}
