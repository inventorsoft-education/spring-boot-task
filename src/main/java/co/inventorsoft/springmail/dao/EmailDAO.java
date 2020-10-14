package co.inventorsoft.springmail.dao;

import co.inventorsoft.springmail.model.Email;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class EmailDAO {
    private final String repLocation;
    private final ObjectMapper mapper;

    @Autowired
    public EmailDAO(Environment environment, ObjectMapper mapper) {
        this.repLocation = Objects.requireNonNull(FileSystemUtils.class.getClassLoader().getResource(""))
                .getPath() + environment.getProperty("emails.storage.location");
        this.mapper = mapper;
    }

    public List<Email> findAll() throws IOException {
        List<Email> emailsList = new ArrayList<>();
        File file = getFileWithEmails();

        if (file.length() > 0){
            emailsList = mapper.readValue(file, new TypeReference<>() {
            });
        }
        if (emailsList == null) {
            emailsList = new ArrayList<>();
        }

        return emailsList;
    }

    public List<Email> emailsToSend() throws IOException {
        List<Email> emailList = findAll();
        LocalDateTime currentDateTime = LocalDateTime.now();

        return emailList
                .stream()
                .filter(e -> e.getDate().isAfter(currentDateTime))
                .collect(Collectors.toList());
    }

    public void replaceAll(List<Email> emails){
        File file = new File(repLocation);
        try {
            mapper.writeValue(file, emails);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveMail(Email email) throws IOException {
        List<Email> emailList = findAll();
        emailList.add(email);
        replaceAll(emailList);
    }

    public void saveAll(List<Email> emails) throws IOException {
        List<Email> emailList = findAll();
        emailList.addAll(emails);
        replaceAll(emailList);
    }

    public void delete(Email email) throws IOException {
        List<Email> emailList = findAll();
        emailList.remove(email);
        replaceAll(emailList);
    }

    public void deleteAll(List<Email> emails) throws IOException {
        List<Email> emailList = findAll();
        emailList.removeAll(emails);
        replaceAll(emailList);
    }

    private File getFileWithEmails() throws IOException {
        File file = new File(repLocation);
        Path filePath = file.toPath();

        if (Files.notExists(filePath)){
            Files.createFile(filePath);
        }

        return file;
    }
}
