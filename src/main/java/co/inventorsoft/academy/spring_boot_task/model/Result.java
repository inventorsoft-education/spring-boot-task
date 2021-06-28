package co.inventorsoft.academy.spring_boot_task.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private String round;
    private Team one;
    private Team two;
    private String gameResult;
}
