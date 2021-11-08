package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan
public class CustomConfig {

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }
}

