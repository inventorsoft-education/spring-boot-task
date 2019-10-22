package com.home_work.spring_boot.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Letter implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String recipient;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String body;

    @Getter
    @Setter
    private LocalDateTime deliveryTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Letter)) return false;
        Letter letter = (Letter) o;
        return Objects.equals(getId(), letter.getId()) &&
                Objects.equals(getRecipient(), letter.getRecipient()) &&
                Objects.equals(getSubject(), letter.getSubject()) &&
                Objects.equals(getBody(), letter.getBody()) &&
                Objects.equals(getDeliveryTime(), letter.getDeliveryTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRecipient(), getSubject(), getBody(), getDeliveryTime());
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", deliveryTime=" + deliveryTime +
                '}';
    }
}
