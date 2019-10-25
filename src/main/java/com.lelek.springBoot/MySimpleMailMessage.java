package com.lelek.springBoot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Getter
@Setter
@Component
class MySimpleMailMessage extends SimpleMailMessage {

    private boolean sent;
    private int id;

    void save() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        List<MySimpleMailMessage> messageList = new ArrayList<>();
        if (this.getId() != 1) {
            messageList = objectMapper
                    .readValue(ConsoleApplication.FILE, new TypeReference<List<MySimpleMailMessage>>() {
                    });
        }
        messageList.add(this);
        objectMapper.writeValue(ConsoleApplication.FILE, messageList);
    }

    static int generateId() throws IOException {
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
