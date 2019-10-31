package com.lelek.springBoot.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lelek.springBoot.ConsoleApplication;
import com.lelek.springBoot.model.MySimpleMailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Repository
public class DefaultMessageDao implements MessageDao {

    @Override
    public List<MySimpleMailMessage> getMessages() {
        try {
            if (!(new Scanner(ConsoleApplication.FILE).hasNext())) {
                return new ArrayList<>();
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                List<MySimpleMailMessage> mailMessageList;
                mailMessageList = objectMapper
                        .readValue(ConsoleApplication.FILE, new TypeReference<List<MySimpleMailMessage>>() {
                        });
                return mailMessageList;
            }
        } catch (IOException e) {
            log.info("Exception: " + e);
            return new ArrayList<>();
        }
    }

    @Override
    public void saveMessage(MySimpleMailMessage mySimpleMailMessage) {
        try {
            mySimpleMailMessage.setId(generateId());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);
            List<MySimpleMailMessage> messageList = new ArrayList<>();
            if (mySimpleMailMessage.getId() != 1) {
                messageList = objectMapper
                        .readValue(ConsoleApplication.FILE, new TypeReference<List<MySimpleMailMessage>>() {
                        });
            }
            messageList.add(mySimpleMailMessage);
            objectMapper.writeValue(ConsoleApplication.FILE, messageList);
        } catch (IOException e) {
            log.info("Exception " + e);
        }
    }

    private static int generateId() throws IOException {
        if (!(new Scanner(ConsoleApplication.FILE).hasNext())) {
            return 1;
        } else {
            List<MySimpleMailMessage> mailMessageList;
            mailMessageList = new ObjectMapper()
                    .configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .readValue(ConsoleApplication.FILE, new TypeReference<List<MySimpleMailMessage>>() {
                    });
            return mailMessageList.size() + 1;
        }
    }

}
