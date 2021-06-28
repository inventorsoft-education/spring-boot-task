package co.inventorsoft.academy.spring_boot_task.mapper;

import co.inventorsoft.academy.spring_boot_task.dto.ResultDto;
import co.inventorsoft.academy.spring_boot_task.model.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TeamMapper.class, componentModel = "spring")
public interface ResultMapper {
    @Mapping(source = "one", target = "one")
    @Mapping(source = "two", target = "two")
    Result toResult(ResultDto resultDto);
}
