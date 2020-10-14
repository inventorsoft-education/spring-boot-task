package com.paskar.email.application.repositiory;


import com.paskar.email.application.console.Email;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface EmailRepository {
   void save(List<Email> email) throws IOException;
    List<Email> findAll() throws IOException;
    List<Email> findEmailsNearDeliveryDate(LocalDateTime currentDate) throws IOException;
}
