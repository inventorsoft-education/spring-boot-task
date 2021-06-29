package co.inventorsoft.academy.spring_boot_task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDto {
    private String name;
    private String captain;
    private String coach;
}
