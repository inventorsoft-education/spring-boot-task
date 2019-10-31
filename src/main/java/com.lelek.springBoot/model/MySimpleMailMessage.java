package com.lelek.springBoot.model;

import com.lelek.springBoot.dto.MessageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@Component
public class MySimpleMailMessage extends SimpleMailMessage {

    private boolean sent;
    private int id;

    public MessageDto mapToDto(){
        return MessageDto.builder()
                .id(this.getId())
                .sent(this.isSent())
                .to(Arrays.toString(this.getTo()))
                .subject(this.getSubject())
                .text(this.getText())
                .date(Objects.requireNonNull(this.getSentDate()).toString())
                .build();
    }

}