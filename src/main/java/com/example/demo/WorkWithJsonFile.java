package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Repository;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Repository
public class WorkWithJsonFile {

   private  ArrayList<Email> emails;
   private  Gson gson;
   private  Email email;

    public WorkWithJsonFile() {
      this.email=new Email();
      this.gson=new GsonBuilder().setPrettyPrinting().create();
      this.emails=loadFromJsonEmailsList();
    }


    //load from json
    public ArrayList<Email> loadFromJsonEmailsList() {
        JsonReader jsonReader = null;
        Type listType = new TypeToken<ArrayList<Email>>() {
        }.getType();
        try {
            jsonReader = new JsonReader(new FileReader("emails"));
            emails = gson.fromJson(jsonReader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return emails;
    }

    //update
    public void updateList(ArrayList<Email> emailsList){
        FileWriter writer = null;
        try {
            writer = new FileWriter("emails");
            writer.write(gson.toJson(emailsList));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEmailToJson(Email tmp){
        if (tmp.getDelieveryDate()!=null) {
            email = tmp;
            emails.add(email);
            updateList(emails);
        }

    }


}
