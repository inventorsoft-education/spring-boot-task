package com.example.demo.Repository;


import com.example.demo.Model.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmailRepository {

    private List<Email> emails;

    public EmailRepository() {
        ObjectMapper mapper = new ObjectMapper();
        List<Email> result = new ArrayList<>();
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get("emails.json")), StandardCharsets.UTF_8);
            Email[] myObjects = mapper.readValue(json, Email[].class);
            result.addAll(Arrays.asList(myObjects));
            //result = Arrays.asList(myObjects);
        } catch (IOException e) {
            //file is empty or not present
        }
        emails = result;
    }

    @PreDestroy
    private void saveEmailsBeforeClosing() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("emails.json"), emails );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Email> getAllEmails(){
        return emails;
    }

    public List<Email> getUnsentEmails(){
        return emails.stream()
                .filter(el -> !el.getSend())
                .collect(Collectors.toList());
    }

    public List<Email> getSentEmails(){
        return emails.stream()
                .filter(Email::getSend)
                .collect(Collectors.toList());
    }

    public List<Email> getEmailsToSend(){
        Date currentDate = new Date();
        return getUnsentEmails().stream()
                .filter(el -> el.getDate().getTime() < currentDate.getTime())
                .collect(Collectors.toList());
    }

    public void add(Email email){
        emails.add(email);
        emails = emails.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
