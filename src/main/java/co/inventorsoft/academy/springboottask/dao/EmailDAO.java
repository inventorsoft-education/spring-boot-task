package co.inventorsoft.academy.springboottask.dao;

import co.inventorsoft.academy.springboottask.model.Email;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class EmailDAO {
    private String emailsLocation;

    @Autowired
    public EmailDAO(Environment environment) {
        this.emailsLocation = Objects.requireNonNull(FileSystemUtils.class.getClassLoader().getResource(""))
                .getPath() + environment.getProperty("emails.storage.location");
    }

    public List<Email> findAll() throws IOException {

        List<Email> emailList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        File file = getFileWithEmails();

        //if file is not empty
        if (file.length() > 0){
            // Deserialize Java object from JSON file.
            emailList = mapper.readValue(file, new TypeReference<List<Email>>(){});
        }

        //If there are no stored emails
        if (emailList == null) {
            emailList = new ArrayList<>();
        }

        return emailList;
    }

    public List<Email> findAllNeededToBeSent() throws IOException {
        //find all emails
        List<Email> emailList = findAll();
        Date currentDate = new Date();

        //filter emails by date
        emailList = emailList
                .stream()
                .filter(e -> e.getDeliveryDate().getTime() <= currentDate.getTime())
                .collect(Collectors.toList());

        return emailList;
    }

    public void replaceAll(List<Email> emails){

        ObjectMapper mapper = new ObjectMapper();

        File file = new File(emailsLocation);
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, emails);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void save(Email email) throws IOException {
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
        File file = new File(emailsLocation);
        Path filePath = file.toPath();

        if (Files.notExists(filePath)){
            Files.createFile(filePath);
        }

        return file;
    }
}
