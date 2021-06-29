package co.inventorsoft.academy.spring_boot_task.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private String message;
    private String description;
}
