package com.lelek.springBoot.dao;

import com.lelek.springBoot.model.MySimpleMailMessage;

import java.util.List;

public interface MessageDao {

    List<MySimpleMailMessage> getMessages();

    void saveMessage(MySimpleMailMessage mySimpleMailMessage);

    MySimpleMailMessage getMessage(long id);

    void removeMessage(long id);

    void updateMessage(long id, MySimpleMailMessage updates);
}
