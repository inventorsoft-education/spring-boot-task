package com.lelek.springBoot.controller;

import com.lelek.springBoot.dto.MessageDto;
import com.lelek.springBoot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessages(){
        return ResponseEntity.ok(messageService.getMessages());
    }

}
