package eu.lundegaard.contactform.service;

import eu.lundegaard.contactform.dto.RequestTypeDTO;
import eu.lundegaard.contactform.dto.StatusDTO;
import eu.lundegaard.contactform.dto.mapper.RequestTypeMapper;
import eu.lundegaard.contactform.repository.RequestTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestTypeService {

    public final static String CACHE_NAME = "requestTypes";

    private final RequestTypeRepository requestTypeRepository;
    private final RequestTypeMapper requestTypeMapper;

    @Cacheable(CACHE_NAME)
    public List<RequestTypeDTO> getAllTypes(){
        return requestTypeRepository.findAll()
                .stream()
                .map(requestTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public StatusDTO resetRequestTypeCache() {
        return new StatusDTO(StatusDTO.Status.SUCCESS);
    }

}
