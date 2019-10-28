package com.academy.task.repository;

import com.academy.task.model.Email;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailRepositoryImpl implements EmailRepository {

    @Value("${email.file}")
    String file;

    Gson gson;

    public EmailRepositoryImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void loadEmailListIntoFile(List<Email> emailList) {
        FileWriter writer;

        try {
            writer = new FileWriter(file);
            writer.write(gson.toJson(emailList));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Email> loadEmailsFromFile() {
        JsonReader jsonReader;
        Type listType = new TypeToken<ArrayList<Email>>() {
        }.getType();

        List<Email> emails = null;
        try {
            jsonReader = new JsonReader(new FileReader(file));
            emails = gson.fromJson(jsonReader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return emails != null ? emails : new ArrayList<>();
    }

    @Override
    public void add(Email email) {
        List<Email> emailList = new ArrayList<>();
        emailList.add(email);

        List<Email> loadEmails = loadEmailsFromFile();
        loadEmails.addAll(emailList);

        loadEmailListIntoFile(loadEmails);
    }

    @Override
    public List<Email> getAll() {
        return loadEmailsFromFile();
    }

    @Override
    public Email getById(Long id) {
        return loadEmailsFromFile().stream()
                .filter(e -> id.equals(e.getId()))
                .findAny().orElse(null);
    }

    @Override
    public List<Email> getEmailsToSend() {
        LocalDateTime currentDate = LocalDateTime.now();

        return loadEmailsFromFile().stream()
                .filter(e -> e.getDate().isBefore(currentDate))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSent(Long id) {
        List<Email> emailList = loadEmailsFromFile();

        List<Email> resultList = emailList.stream()
                .filter(e -> e.getId().equals(id))
                .collect(Collectors.toList());

        emailList.removeAll(resultList);

        loadEmailListIntoFile(emailList);
    }

    @Override
    public void update(Long id, Email email) {
        List<Email> emailList = loadEmailsFromFile();

        List<Email> resultList = emailList.stream()
                .filter(e -> e.getId().equals(id))
                .collect(Collectors.toList());

        Email emailFromDB = resultList.get(0);
        emailFromDB.setRecipient(email.getRecipient());
        emailFromDB.setSubject(email.getSubject());
        emailFromDB.setBody(email.getBody());
        emailFromDB.setDate(email.getDate());

        loadEmailListIntoFile(emailList);
    }

}
