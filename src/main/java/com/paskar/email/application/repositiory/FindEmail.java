package com.paskar.email.application.repositiory;

import com.paskar.email.application.console.Email;

import java.time.LocalDateTime;
import java.util.List;

public interface FindEmail {
    List<Email> findAll();
    List<Email> findEmailsNearDeliveryDate(LocalDateTime currentDate);

}
