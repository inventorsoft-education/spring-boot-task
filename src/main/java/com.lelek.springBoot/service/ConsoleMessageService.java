package com.lelek.springBoot.service;

import com.lelek.springBoot.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class ConsoleMessageService {

    private static final String NEW_MESSAGE = "N";
    private static final String QUITE = "Q";
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Autowired
    private MessageDto messageDto;

    @Autowired
    private MessageService messageService;

    public boolean startConsoleMessaging() {
        try {
            System.out.print("Enter email: ");
            messageDto.setTo(br.readLine());
            System.out.print("Enter subject: ");
            messageDto.setSubject(br.readLine());
            System.out.print("Enter message: ");
            messageDto.setText(br.readLine());
            System.out.print("Enter delivery date and time (dd.MM.yyyy HH:mm): ");
            messageDto.setDate(br.readLine());
            messageService.saveMessage(messageDto);
            System.out.print("Enter \"N\" if You want add new message,  enter \"Q\" if You want shut down app: ");
            return restart(br.readLine());
        } catch (IOException e) {
            log.error("ConsoleMessageService exception: " + e);
            return true;
        }
    }

    private boolean restart(String input) throws IOException{
        if (NEW_MESSAGE.equals(input)) {
            startConsoleMessaging();
            return false;
        } else if (QUITE.equals(input)) {
            return true;
        } else {
            System.out.print("Enter \"N\" if You want add new message,  enter \"Q\" if You want shut down app: ");
            restart(br.readLine());
            return false;
        }
    }
}
