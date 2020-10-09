package com.paskar.email.application.interafaces;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Email {
    private String recipient;
    private String subject;
    private String body;
    private LocalDateTime date;
}
