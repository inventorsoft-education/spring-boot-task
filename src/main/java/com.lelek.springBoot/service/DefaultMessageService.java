package com.lelek.springBoot.service;

import com.lelek.springBoot.dao.MessageDao;
import com.lelek.springBoot.dto.MessageDto;
import com.lelek.springBoot.model.MySimpleMailMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class DefaultMessageService implements MessageService{

    private MessageDao messageDao;

    private MySimpleMailMessage mySimpleMailMessage;

    public List<MySimpleMailMessage> getMessages() {
        return messageDao.getMessages();
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
            log.error("error" + e);
        }
        mySimpleMailMessage.setSent(false);
        messageDao.saveMessage(mySimpleMailMessage);
    }
}
