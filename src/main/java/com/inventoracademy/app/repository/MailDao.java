package com.inventoracademy.app.repository;

import com.inventoracademy.app.model.Mail;

import java.io.IOException;
import java.util.List;

public interface MailDao {
    void save(List<Mail> email) throws IOException;

    List<Mail> findAll() throws IOException;

    List<Mail> findEmailsNearDeliveryDate() throws IOException;

    void deletingMassagesThatWereSent() throws IOException;
}
