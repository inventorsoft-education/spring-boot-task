package com.example.demo;

import com.example.demo.model.Match;
import com.example.demo.repository.TeamRepository;
import com.example.demo.model.Team;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Menu();
    }

    public void Menu() {
        Scanner in = new Scanner(System.in);
        System.out.println("\tMenu");
        System.out.println("1 - Add a team");
        System.out.println("2 - See the result of match");
        System.out.println("3 - See the winner of the tournament");
        System.out.println("4 - See the upcoming mathces");
        System.out.println("Enter the item number");

        switch (in.nextInt()) {
            case 1:

        }
    }
}
