package com.example.demo.console;

import com.example.demo.repo.JsonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    private JsonRepo jsonRepo;

    @Override
    public void run(String... args) throws Exception {
        jsonRepo.loadMessageToJson();
    }
}