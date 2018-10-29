package spring.boot.task.repositories;
import org.springframework.mail.SimpleMailMessage;
import java.util.Date;

public interface EmailRepository {
    String writeRecipientAddress();
    String writeSubject();
    String writeText();
    Date writeDate();
    SimpleMailMessage readEmail();
}
