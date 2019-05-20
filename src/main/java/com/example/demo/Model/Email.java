package com.example.demo.Model;

import java.util.Date;
import java.util.Objects;

public class Email{

    private String recipient;

    private String subject;

    private String body;

    private Date date;

    private Boolean isSend;

    public Email(String recipient, String subject, String body, Date date, Boolean isSend) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.date = date;
        this.isSend = isSend;
    }

    public Email() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getSend() {
        return isSend;
    }

    public void setSend(Boolean send) {
        isSend = send;
    }

    @Override
    public String toString() {
        return "Email{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", isSend=" + isSend +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(recipient, email.recipient) &&
                Objects.equals(subject, email.subject) &&
                Objects.equals(body, email.body) &&
                Objects.equals(date, email.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, subject, body, date);
    }

}
