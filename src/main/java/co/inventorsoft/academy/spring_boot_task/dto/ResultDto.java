package co.inventorsoft.academy.spring_boot_task.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultDto {
    private String round;
    private TeamDto oneDto;
    private TeamDto twoDto;
    private String gameResult;
}
