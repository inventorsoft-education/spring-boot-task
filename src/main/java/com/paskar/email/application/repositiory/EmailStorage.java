package com.paskar.email.application.repositiory;


import com.fasterxml.jackson.core.type.TypeReference;
import com.paskar.email.application.console.Email;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailStorage implements EmailRepository {
    private final static String baseFile = "emailList.json";

    private final OwnMapper mapper;

    public EmailStorage(OwnMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(List<Email> email) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(baseFile, true)))) {
            mapper.writeValue(out, email);
        }
    }


    @Override
    public List<Email> findAll() throws IOException {
        return mapper.readValue(new File(baseFile), new TypeReference<>() {
        });
    }

    @Override
    public List<Email> findEmailsNearDeliveryDate(LocalDateTime currentDate) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Email> emailList = findAll();
        List<Email> result = new ArrayList<>();
        for (Email email : emailList) {
            if (time.equals(email.getDate())) {
                return result;
            }
        }
        return null;
    }
}
