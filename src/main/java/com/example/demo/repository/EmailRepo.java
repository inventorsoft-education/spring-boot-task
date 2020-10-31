package com.example.demo.repository;

import com.example.demo.consoleInfo.Email;

import java.io.IOException;
import java.util.List;

public interface EmailRepo {

    void save(List<Email> email) throws IOException;

    List<Email> findAllEmails() throws IOException;

    List<Email> showEmailsForSending() throws IOException;

    void deleteSentEmails() throws IOException;
}
