package com.example.demo.tournament;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Configuration
public class Config {

    @Bean
    public PrintWriter writerBean() throws FileNotFoundException {
        return new PrintWriter("write_file.csv");
    }

    @Bean
    public ConsoleWriter consoleBean() {
        return new ConsoleWriter();
    }
}
