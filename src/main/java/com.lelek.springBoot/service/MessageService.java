package com.lelek.springBoot.service;

import com.lelek.springBoot.dto.MessageDto;
import com.lelek.springBoot.model.MySimpleMailMessage;

import java.util.List;

public interface MessageService {

    List<MessageDto> getMessages();

    void saveMessage(MessageDto messageDto);

}
