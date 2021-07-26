package eu.lundegaard.contactform.dto.mapper;

import eu.lundegaard.contactform.dto.RequestTypeDTO;
import eu.lundegaard.contactform.repository.entity.RequestTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestTypeMapper {

    RequestTypeDTO toDTO(RequestTypeEntity source);
    RequestTypeEntity toEntity(RequestTypeDTO source);

}
