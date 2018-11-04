package com.sender.email;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Email implements Serializable {
    private String recipient;

    private String subject;

    private String body;

    private Date deliveryDate;

    private Boolean isSent;

    Email() {
        recipient = "A1lexen30@gmail.com";
        subject = "TEST!";
        body = "TEST";
        deliveryDate = new Date();
        isSent = false;
    }

    @Override
    public String toString() {
        return "\nRecipient: " + recipient + "\n Subject: " + subject + " Delivery date: " + deliveryDate + "\n isSent: " + isSent;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(recipient.equals(((Email)obj).recipient)
                && subject.equals(((Email)obj).subject)
                && deliveryDate.equals(((Email)obj).deliveryDate)) {
            return true;
        }
        return false;
    }

}
