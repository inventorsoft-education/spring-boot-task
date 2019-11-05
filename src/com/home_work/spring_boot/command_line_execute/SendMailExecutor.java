package com.home_work.spring_boot.command_line_execute;


import com.home_work.spring_boot.entity.Letter;
import com.home_work.spring_boot.services.MailService;
import com.home_work.spring_boot.threads.MailSenderThread;
import com.home_work.spring_boot.ui.UIRepresentation;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;

@Component
public class SendMailExecutor {


    private UIRepresentation uiRepresentation;
    private ExecutorService executorService;
    private MailService mailService;

    public SendMailExecutor(UIRepresentation uiRepresentation, ExecutorService executorService,
                            MailService mailService) {
        this.uiRepresentation = uiRepresentation;
        this.executorService = executorService;
        this.mailService = mailService;
    }

    public void execute() {

        Letter letter;
        Scanner scanner = new Scanner(System.in);
        String askForSendLetter = uiRepresentation.askForSendLetter(scanner);
        while (askForSendLetter.equals("yes")) {
            letter = uiRepresentation.createLetter();
            executorService.execute(new MailSenderThread(mailService, letter));
            askForSendLetter = uiRepresentation.askForSendLetter(scanner);
        }
        executorService.shutdown();
    }
}
