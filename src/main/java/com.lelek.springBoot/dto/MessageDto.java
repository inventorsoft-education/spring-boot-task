package com.lelek.springBoot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MessageDto {
    private int id;
    private boolean sent;
    private String to;
    private String subject;
    private String text;
    private String date;
}
