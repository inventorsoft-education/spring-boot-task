package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

    private ConsoleReadWrite consoleReadWrite;

    @Autowired
    public SpringbootApplication(ConsoleReadWrite consoleReadWrite) {
        this.consoleReadWrite = consoleReadWrite;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleReadWrite.start();
    }

}
