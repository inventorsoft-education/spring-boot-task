package com.lelek.springBoot.configurations;

import com.lelek.springBoot.dao.MessageDao;
import com.lelek.springBoot.service.SenderService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;

@AllArgsConstructor
@Configuration
public class AppConfiguration {

    private JavaMailSender javaMailSender;

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
