package com.example.demo.repo;

import com.example.demo.model.Message;
import com.example.demo.model.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    public void addMessage(Message message) {
        messageLists.add(message);

        updateListJson(messageLists);
    }

    public Message findById(Long id) {
        return messageLists.stream()
                .filter(a -> id.equals(a.getId()))
                .findAny().orElse(null);
    }

    public void changeDateById(long id, long date) {
        Message message = findById(id);
        message.setFutureSecond(date);
        deleteById(id);
        messageLists.add(message);
        updateListJson(messageLists);
    }

    public void changeStatusById(int id, Message message) {
        message.setStatus(Status.SENT);
        messageLists.set(id, message);
        updateListJson(messageLists);
    }

    public void deleteById(long id) {
        Message message;
        Iterator iterator = messageLists.iterator();
        while (iterator.hasNext()) {
            message = (Message) iterator.next();
            if (message.getId() == id) {
                iterator.remove();
            }
        }
        updateListJson(messageLists);
    }

    public void loadMessageToJson() {

        message.setId(messageLists.size());
        message.setMessage(InputValue.inputMessage());
        message.setEmailTo(InputValue.inputEmail());
        message.setSubject(InputValue.inputSubject());
        message.setFutureSecond(InputValue.inputSecond());
        message.setStatus(Status.NOT_SENT);
        message.setCurrentTime(new Date());

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

    public List<Message> loadMessagesDoNotSent() {
        return loadFromJsonToList().stream()
                .filter(ml -> ml.getStatus().equals(Status.NOT_SENT))
                .collect(Collectors.toList());
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

    @PreDestroy
    public void shutdown() {
        System.out.println("Pre destroy method -----------------------------------------------");
        updateListJson(messageLists);
    }
}
