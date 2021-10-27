package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(SpringConfig.class);
		SpringApplication.run(Application.class, args);


	}

}
