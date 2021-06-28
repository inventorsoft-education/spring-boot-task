package co.inventorsoft.academy.spring_boot_task.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Team {
    private String name;
    private String captain;
    private String couch;
}
