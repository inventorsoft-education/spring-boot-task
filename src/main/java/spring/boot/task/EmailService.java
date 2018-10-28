package spring.boot.task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class EmailService {
    private EmailConsoleReader emailConsoleReader;
    private String fileName = "email";
    @Autowired
    public EmailService(EmailConsoleReader emailConsoleReader){
        this.emailConsoleReader = emailConsoleReader;
    }
    public SimpleMailMessage getEmail() throws IOException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        if (Files.exists(Paths.get("email"))) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                simpleMailMessage = (SimpleMailMessage) ois.readObject();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            clearEmail();
        } else {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                simpleMailMessage = emailConsoleReader.readFromConsole();
                oos.writeObject(simpleMailMessage);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return simpleMailMessage;
    }
    public void clearEmail() throws IOException {
        if (Files.exists(Paths.get("email"))){
            Files.delete(Paths.get("email"));
        }
    }
}
