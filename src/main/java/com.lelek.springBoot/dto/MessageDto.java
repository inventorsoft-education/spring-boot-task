package com.lelek.springBoot.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MessageDto {
    private String to;
    private String subject;
    private String text;
    private String date;
}
