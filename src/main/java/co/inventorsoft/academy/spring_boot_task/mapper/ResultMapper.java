package co.inventorsoft.academy.spring_boot_task.mapper;

import co.inventorsoft.academy.spring_boot_task.dto.ResultDto;
import co.inventorsoft.academy.spring_boot_task.model.Result;
import org.mapstruct.Mapper;

@Mapper(uses = TeamMapper.class, componentModel = "spring")
public interface ResultMapper {
    Result toResult(ResultDto resultDto);
}
