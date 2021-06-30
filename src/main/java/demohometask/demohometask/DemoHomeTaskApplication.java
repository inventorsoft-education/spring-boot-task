package demohometask.demohometask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoHomeTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoHomeTaskApplication.class, args);
        System.out.println("##### WELCOME TO THE TOURNAMENT ! #####");
        Match.start();
    }
}
