package com.lelek.springBoot.service;

import com.lelek.springBoot.dao.MessageDao;
import com.lelek.springBoot.dto.MessageDto;
import com.lelek.springBoot.model.MySimpleMailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultMessageService implements MessageService{

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MySimpleMailMessage mySimpleMailMessage;

    public List<MessageDto> getMessages() {
        return messageDao.getMessages().stream()
        .map(MySimpleMailMessage::mapToDto)
                .collect(Collectors.toList());
    }

    public void saveMessage(MessageDto messageDto){
        mySimpleMailMessage.setTo(messageDto.getTo());
        mySimpleMailMessage.setCc(messageDto.getTo());
        mySimpleMailMessage.setBcc(messageDto.getTo());
        mySimpleMailMessage.setSubject(messageDto.getSubject());
        mySimpleMailMessage.setText(messageDto.getText());
        try {
            mySimpleMailMessage.setSentDate(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(messageDto.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mySimpleMailMessage.setSent(false);
        messageDao.saveMessage(mySimpleMailMessage);
    }
}
