package com.paskar.email.application.repositiory;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paskar.email.application.console.Email;
import com.paskar.email.application.console.InformationFromConsole;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class EmailStorage implements EmailRepository {
    private final static String baseFile = "emailList.json";

    private final InformationFromConsole console;

    public EmailStorage(InformationFromConsole console) {
        this.console = console;
    }

    @Override
    public void save(List<Email> email) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), email);
    }

    @Override
    public Email createNewEmail() throws IOException {
        String recipient = console.recipientValidation();
        String subject = console.emailSubject();
        String body = console.bodyValidation();
        LocalDateTime date = console.dateValidation();
        return new Email(recipient, subject, body, date);
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
