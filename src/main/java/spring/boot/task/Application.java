package spring.boot.task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import spring.boot.task.services.EmailService;

@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        EmailService emailService = (EmailService) context.getBean("emailServiceImpl");
        emailService.sendScheduledEmail();
    }
}
