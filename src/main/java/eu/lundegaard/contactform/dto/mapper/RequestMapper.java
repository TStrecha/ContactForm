package eu.lundegaard.contactform.dto.mapper;

import eu.lundegaard.contactform.dto.RequestDTO;
import eu.lundegaard.contactform.repository.entity.RequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(target = "requestType", ignore = true)
    RequestEntity toEntity(RequestDTO source);

}
