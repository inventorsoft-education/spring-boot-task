package com.lelek.springBoot.service;

import com.lelek.springBoot.dto.MessageDto;

import java.util.List;

public interface MessageService {

    List<MessageDto> getMessages();

    void saveMessage(MessageDto messageDto);

    MessageDto getMessage(long id);

    void removeMessage(long id);

    void updateMessage(long id, MessageDto updates);

}
