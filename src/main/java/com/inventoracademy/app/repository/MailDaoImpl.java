package com.inventoracademy.app.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoracademy.app.model.Mail;
import org.springframework.stereotype.Component;


import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MailDaoImpl implements MailDao {

    private final static String mailDB = "emails.json";

    private final ObjectMapper mapper;

    public MailDaoImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(List<Mail> email) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(mailDB, true)))) {
            mapper.writeValue(out, email);
        }
    }

    @Override
    public List<Mail> findAll() throws IOException {
        return mapper.readValue(new File(mailDB), new TypeReference<>() {
        });
    }

    @Override
    public List<Mail> findEmailsNearDeliveryDate() throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Mail> mailList = findAll();
        List<Mail> result = new ArrayList<>();

        for (Mail mail : mailList) {
            if (time.equals(mail.getDate())) {
                result.add(mail);
            }
        }
        return result;
    }

    @Override
    public void deletingMassagesThatWereSent() throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Mail> mailList = findAll();
        List<Mail> result = new ArrayList<>();

        for (Mail mail : mailList) {
            if (!time.equals(mail.getDate())) {
                result.add(mail);
            }
        }
        mapper.writeValue(new File(mailDB), result);
    }
}
