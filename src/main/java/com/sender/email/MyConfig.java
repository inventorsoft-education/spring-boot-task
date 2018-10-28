package com.sender.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class MyConfig {

    @Bean
    public TaskScheduler getScheduler(){
        TaskScheduler scheduler = new ThreadPoolTaskScheduler();
        ((ThreadPoolTaskScheduler) scheduler).setPoolSize(5);
        ((ThreadPoolTaskScheduler) scheduler).setThreadNamePrefix("EmailSending");
        ((ThreadPoolTaskScheduler) scheduler).initialize();

        return scheduler;
    }


    @Bean
    public SimpleMailMessage getMessage() {
        return new SimpleMailMessage();
    }
}
