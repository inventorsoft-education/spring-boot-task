package com.example.demo.repo;

import com.example.demo.model.Message;
import com.example.demo.model.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JsonRepo {

    private List<Message> messageLists;
    private Gson gson;
    private Message message;

    public JsonRepo() {
        this.message = new Message();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.messageLists = loadFromJsonToList();
    }

    public void updateListJson(List<Message> list) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("mess.json");
            writer.write(gson.toJson(messageLists));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMessageToJson() {

        message.setId(messageLists.size());
        message.setMessage(InputValue.inputMessage());
        message.setMessage(InputValue.inputEmail());
        message.setSubject(InputValue.inputSubject());
        message.setFutureDate(InputValue.inputSecond());
        message.setStatus(Status.NOT_SENT);

        messageLists.add(message);

        updateListJson(messageLists);

        if (InputValue.resend()) {
            loadMessageToJson();
        }
    }

    public List<Message> loadFromJsonToList() {
        JsonReader jsonReader = null;
        Type listType = new TypeToken<List<Message>>() {
        }.getType();
        try {
            jsonReader = new JsonReader(new FileReader("mess.json"));
            messageLists = gson.fromJson(jsonReader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return messageLists != null ? messageLists : new ArrayList<>();
    }
}
