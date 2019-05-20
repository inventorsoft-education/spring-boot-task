package com.example.demo;


import com.example.demo.Model.Email;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> getUnsentEmails(){
        return emailRepository.readEmailsFromFile().stream()
                .filter(el -> !el.getSend())
                .collect(Collectors.toList());
    }

    public List<Email> getSentEmails(){
        return emailRepository.readEmailsFromFile().stream()
                .filter(Email::getSend)
                .collect(Collectors.toList());
    }

    public List<Email> getAllEmails(){
        return emailRepository.readEmailsFromFile();
    }

    public List<Email> getEmailsToSend(){
        Date currentDate = new Date();
        return getUnsentEmails().stream()
                .filter(el -> el.getDate().getTime() < currentDate.getTime())
                .collect(Collectors.toList());
    }

    public void add(Email email){
        List<Email> presentEmails = emailRepository.readEmailsFromFile();

        List<Email> newEmails = new ArrayList<>();
        newEmails.addAll(presentEmails);
        newEmails.add(email);

        List<Email> distinctOnly = newEmails.stream()
                .distinct()
                .collect(Collectors.toList());


        emailRepository.saveEmailsToFile(distinctOnly);
    }

    public void add(List<Email> list){
        List<Email> presentEmails = emailRepository.readEmailsFromFile();

        List<Email> newEmails = new ArrayList<>();
        newEmails.addAll(presentEmails);
        newEmails.addAll(list);

        List<Email> distinctOnly = newEmails.stream()
                .distinct()
                .collect(Collectors.toList());

        emailRepository.saveEmailsToFile(distinctOnly);
    }
    public void setSend(Email email){
        List<Email> presentEmails = emailRepository.readEmailsFromFile();

        List<Email> newEmails = new ArrayList<>();
        newEmails.addAll(presentEmails);

        newEmails.get(newEmails.indexOf(email)).setSend(email.getSend());

        emailRepository.saveEmailsToFile(newEmails);
    }


}
