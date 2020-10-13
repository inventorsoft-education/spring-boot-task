package com.paskar.email.application.repositiory;


import com.paskar.email.application.console.Email;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmailRepository {
   void save(List<Email> email) throws IOException;
    Email createNewEmail() throws IOException;
    List<Email> findAll() throws IOException;
    Optional<Email> findEmailsNearDeliveryDate(LocalDateTime currentDate) throws IOException;
}