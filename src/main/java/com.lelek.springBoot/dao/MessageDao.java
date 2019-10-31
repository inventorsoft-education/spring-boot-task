package com.lelek.springBoot.dao;

import com.lelek.springBoot.model.MySimpleMailMessage;

import java.util.List;

public interface MessageDao {

    List<MySimpleMailMessage> getMessages();

    void saveMessage(MySimpleMailMessage mySimpleMailMessage);
}
