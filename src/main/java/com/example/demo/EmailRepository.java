package com.example.demo;


import com.example.demo.Model.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EmailRepository {

    public boolean saveEmailsToFile(List<Email> list) {

        Boolean result;
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("emails.json"), list );
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
    public List<Email> readEmailsFromFile()  {

        ObjectMapper mapper = new ObjectMapper();
        List<Email> result = new ArrayList<>();
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get("emails.json")), StandardCharsets.UTF_8);
            Email[] myObjects = mapper.readValue(json, Email[].class);
            result = Arrays.asList(myObjects);
        } catch (IOException e) {
            //file is empty or not present
        }

        return result;
    }
}
