package com.example.lesson4;

import org.springframework.stereotype.Component;

@Component
class Message {

    private String recipientEmail;
    private String emailSubject;
    private String emailBody;
    private String deliveryDate;
}
