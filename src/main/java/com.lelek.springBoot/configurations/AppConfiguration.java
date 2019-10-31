package com.lelek.springBoot.configurations;

import com.lelek.springBoot.dao.MessageDao;
import com.lelek.springBoot.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class AppConfiguration {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageDao messageDao;

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
        return args -> {
            SenderService senderService = new SenderService(javaMailSender, messageDao);
            senderService.setDaemon(true);
            executor.execute(senderService);
        };
    }
}
