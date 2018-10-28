package spring.boot.task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import java.io.IOException;

@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        EmailSender emailSender = (EmailSender) context.getBean("emailSender");
        emailSender.sendScheduledEmail();
    }
}
