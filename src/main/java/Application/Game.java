package Application;

import Application.Managers.GameManager;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringBootApplication
public class Game implements CommandLineRunner {

    static GameManager gameManager;


    @Autowired
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    public static void main(String[] args) {
        SpringApplication.run(Game.class, args);
    }

    @Override
    public void run(String... args) {
        gameManager.gameFlow();
    }
}
