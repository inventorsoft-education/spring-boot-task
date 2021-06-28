package co.inventorsoft.academy.spring_boot_task.mapper;

import co.inventorsoft.academy.spring_boot_task.dto.TeamDto;
import co.inventorsoft.academy.spring_boot_task.model.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    Team toTeam(TeamDto teamDto);
    TeamDto toTeamDto(Team team);
}
