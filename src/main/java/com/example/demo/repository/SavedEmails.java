package com.example.demo.repository;

import com.example.demo.consoleInfo.Email;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class SavedEmails implements EmailRepo {
    private final static String fileForStorage = "listOfEmails.json";
    private final ObjectMapper mapper;
    public SavedEmails(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public void save(List<Email> email) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(fileForStorage, true)))) {
            mapper.writeValue(out,email);
        }
    }
    @Override
    public List<Email> findAllEmails() throws IOException {
        return mapper.readValue(new File(fileForStorage), new TypeReference<>() {
        });
    }

    @Override

        public List<Email> showEmailsForSending() throws IOException {
            LocalDateTime currentTime = LocalDateTime.now();
            List<Email> emailList = findAllEmails();
            List<Email> result = new ArrayList<>();

            for (Email email : emailList) {
                if (currentTime.equals(email.getDate())) {

                    result.add(email);
                }
            }
            return result;
        }

        @Override
        public void deleteSentEmails() throws IOException {
            LocalDateTime currentTime = LocalDateTime.now();
            List<Email> emailList = findAllEmails();
            List<Email> resultList = new ArrayList<>();

            for (Email email : emailList) {
                if (!currentTime.equals(email.getDate())) {
                    resultList.add(email);
                }
            }

            mapper.writeValue(new File(fileForStorage), resultList);
        }
    }


