package spring.boot.task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import spring.boot.task.services.EmailService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        EmailService emailService = context.getBean(EmailService.class);
        emailService.sendScheduledEmail(emailService.getEmail());
    }
}
