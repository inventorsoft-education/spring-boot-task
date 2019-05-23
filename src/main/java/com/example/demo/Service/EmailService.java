package com.example.demo.Service;


import com.example.demo.Model.Email;
import com.example.demo.Repository.EmailRepository;
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

    public void markAsSent(Email email){
        List<Email> emails = emailRepository.getAllEmails();
        emails.get(emails.indexOf(email)).setSend(email.getSend());
    }

    public void add(Email email){
        emailRepository.add(email);
    }

    public List<Email> getAllEmails(){
        return emailRepository.getAllEmails();
    }

    public List<Email> getSentEmails(){
        return emailRepository.getSentEmails();
    }

    public List<Email> getUnsentEmails(){
        return emailRepository.getUnsentEmails();
    }
    public List<Email> getEmailsToSend(){
        return emailRepository.getEmailsToSend();
    }

}
