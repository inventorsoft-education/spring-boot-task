package com.home_work.spring_boot.entity;

import com.home_work.spring_boot.annotations.Reachable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class Letter implements Serializable {

    private Long id;

    @Email
    @NotBlank
    private String recipient;

    @NotBlank
    @Length(min = 3, max = 128)
    private String subject;

    @NotBlank
    @Length(min = 3, max = 128)
    private String body;

    @Reachable
    @NotNull
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
