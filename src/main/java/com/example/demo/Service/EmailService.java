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

    public List<Email> getUnsentEmails(){
        //I'm not sure if i should invoke method from repository, or it's 'wrapper' from this service.
        return emailRepository.readEmailsFromFile().stream()
                .filter(el -> !el.getSend())
                .collect(Collectors.toList());
    }

    public List<Email> getSentEmails(){
        //Same here.
        return emailRepository.readEmailsFromFile().stream()
                .filter(Email::getSend)
                .collect(Collectors.toList());
    }

    public List<Email> getAllEmails(){
        return emailRepository.readEmailsFromFile();
    }

    public List<Email> getEmailsToSend(){
        //Returns emails that haven' been sent earlier.
        //In order to know if the date has passed or not i compare email's getDate with current date,
        //... by casting both of them to milliseconds.
        Date currentDate = new Date();
        return getUnsentEmails().stream()//Re-using code from above.
                .filter(el -> el.getDate().getTime() < currentDate.getTime())
                .collect(Collectors.toList());
    }

    public void add(Email email){
        List<Email> presentEmails = emailRepository.readEmailsFromFile();

        //I used Arrays.asList method in emailRepository, so the list it returns is immutable.
        //In order to modify it i have to create new list, and then add all elements from the first list to it.
        //It's a kostyl', isn't it? How do i bypass it?

        List<Email> newEmails = new ArrayList<>();//new list
        newEmails.addAll(presentEmails);//there go all elements, that were already present
        newEmails.add(email);//... and the ones that i have to add

        List<Email> distinctOnly = newEmails.stream()//Remove copies. Just in case.
                .distinct()
                .collect(Collectors.toList());


        emailRepository.saveEmailsToFile(distinctOnly);
        //So basically i read list from .JSON file, then add new ones and re-write the file.
    }

    public void markAsSent(Email email){
        List<Email> presentEmails = emailRepository.readEmailsFromFile();
        //I faced an issue in which in order to mark 1 email as sent, i have to re-write whole json file.
        //And i genuinely dunno how to bypass it as well as an issue above.
        List<Email> newEmails = new ArrayList<>();
        newEmails.addAll(presentEmails);

        newEmails.get(newEmails.indexOf(email)).setSend(email.getSend());

        emailRepository.saveEmailsToFile(newEmails);
    }


}
