package com;

import com.game_controllers.MainController;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {
    private final MainController mainController;

    public static void main(String... args){
        SpringApplication.run(Application.class,args);
    }
    @Override
    public void run(String... args) throws Exception {
        mainController.startApp();
    }
}
