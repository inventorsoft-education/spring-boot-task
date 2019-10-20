package com.academy.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Email {

    private Long id;

    private String recipient;

    private String subject;

    private String body;

    private LocalDateTime date;

}