package com.inventoracademy.app.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class Mail {
    private String email;
    private String subject;
    private String body;
    private LocalDateTime date;

    public Mail() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return Objects.equals(email, mail.email) &&
                Objects.equals(subject, mail.subject) &&
                Objects.equals(body, mail.body) &&
                Objects.equals(date, mail.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, subject, body, date);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Mail(String email, String subject, String body, LocalDateTime date) {
        this.email = email;
        this.subject = subject;
        this.body = body;
        this.date = date;
    }
}
