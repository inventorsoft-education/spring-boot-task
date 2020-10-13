package com.paskar.email.application.repositiory;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paskar.email.application.console.Email;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class EmailStorage implements EmailRepository {
    private final static String baseFile = "emailList.json";

    @Override
    public void save(List<Email> email) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(baseFile, true))); // append mode file writer
        mapper.writeValue(out, email);
    }

    @Override
    public Email createNewEmail() throws IOException {
        return null;
    }

    @Override
    public List<Email> findAll() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), new TypeReference<List<Email>>() {
        });
    }

    @Override
    public Optional<Email> findEmailsNearDeliveryDate(LocalDateTime currentDate) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Email> emailList = findAll();
        for (Email email : emailList) {
            if (time.equals(email.getDate())) {
                return Optional.of(email);
            }
        }
        return Optional.empty();
    }
}
