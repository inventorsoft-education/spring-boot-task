package co.inventorsoft.academy.springboottask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Email {
    private String deliveryAddress;
    private String subject;
    private String text;
    private Date deliveryDate;
}
