package co.inventorsoft.springmail.dao;

import co.inventorsoft.springmail.model.Email;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileSystemUtils;

import javax.annotation.PostConstruct;
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
public class EmailDAO implements CrudDao{
    private final String repLocation;
    private final ObjectMapper mapper;

    @Autowired
    public EmailDAO(Environment environment, ObjectMapper mapper) {
        this.repLocation = Objects.requireNonNull(FileSystemUtils.class.getClassLoader().getResource(""))
                .getPath() + environment.getProperty("emails.storage.location");
        this.mapper = mapper;
    }

    @PostConstruct
    public void setUp() {
        mapper.registerModule(new JavaTimeModule());
    }

    private File getFileWithEmails() throws IOException {
        File file = new File(repLocation);
        Path filePath = file.toPath();

        if (Files.notExists(filePath)){
            Files.createFile(filePath);
        }

        return file;
    }

    @Override
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

    @Override
    public void saveMail(Email email) throws IOException {
        List<Email> emailList = findAll();
        emailList.add(email);
        replaceAll(emailList);
    }

    @Override
    public void delete(Email email) throws IOException {
        List<Email> emailList = findAll();
        emailList.remove(email);
        replaceAll(emailList);
    }

    @Override
    public void deleteAll(List emails) throws IOException {
        List<Email> emailList = findAll();
        emailList.removeAll(emails);
        replaceAll(emailList);
    }

    @Override
    public void saveAll(List emails) throws IOException {
        List<Email> emailList = findAll();
        emailList.addAll(emails);
        replaceAll(emailList);
    }

    @Override
    public void replaceAll(List emails) {
        File file = new File(repLocation);
        try {
            mapper.writeValue(file, emails);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
