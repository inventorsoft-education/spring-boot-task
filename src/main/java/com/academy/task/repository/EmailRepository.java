package com.academy.task.repository;

import com.academy.task.model.Email;
import java.util.List;

public interface EmailRepository {

    void loadEmailListIntoFile(List<Email> emailList);

    List<Email> loadEmailsFromFile();

    List<Email> getAll();

    Email getById(Long id);

    List<Email> getEmailsToSend();

    void add(Email email);

    void update(Long id, Email email);

    void deleteSent(Long id);

}
