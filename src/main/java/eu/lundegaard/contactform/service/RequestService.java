package eu.lundegaard.contactform.service;

import eu.lundegaard.contactform.configuration.ContactFormConfig;
import eu.lundegaard.contactform.constant.StatusEnum;
import eu.lundegaard.contactform.constant.ValidationErrorConstants;
import eu.lundegaard.contactform.dto.RequestDTO;
import eu.lundegaard.contactform.dto.RequestStatusDTO;
import eu.lundegaard.contactform.dto.UserValidationErrorDTO;
import eu.lundegaard.contactform.dto.mapper.RequestMapper;
import eu.lundegaard.contactform.repository.RequestRepository;
import eu.lundegaard.contactform.repository.RequestTypeRepository;
import eu.lundegaard.contactform.repository.entity.RequestEntity;
import eu.lundegaard.contactform.repository.entity.RequestTypeEntity;
import eu.lundegaard.contactform.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestMapper requestMapper;
    private final RequestRepository requestRepository;
    private final RequestTypeRepository requestTypeRepository;
    private final ContactFormConfig contactFormConfig;


    public RequestStatusDTO createRequest(RequestDTO request){
        RequestStatusDTO result = new RequestStatusDTO();
        RequestTypeEntity requestTypeEntity = requestTypeRepository.getOne(request.getRequestType());
        List<UserValidationErrorDTO> errors = validateRequest(request, requestTypeEntity);

        //If any error found, the request won't save.
        if(errors.size() > 0){
            result.setStatus(StatusEnum.FAILURE);
            result.setErrors(errors);

            return result;
        }
        result.setStatus(StatusEnum.SUCCESS);

        RequestEntity requestEntity = requestMapper.toEntity(request);

        requestEntity.setRequestType(requestTypeEntity);
        requestTypeEntity.getRequests().add(requestEntity);

        requestRepository.save(requestEntity);

        return result;
    }

    private List<UserValidationErrorDTO> validateRequest(RequestDTO request, RequestTypeEntity requestTypeEntity) {
        List<UserValidationErrorDTO> result = new ArrayList<>();

        if(requestTypeEntity == null){
            UserValidationErrorDTO error = new UserValidationErrorDTO(ValidationErrorConstants.INVALID_REQUEST_TYPE);
            error.setInvalidValue(request.getRequestType());
            error.setPropertyPath("RequestDTO#requestType");
            result.add(error);
        }

        if(request.getName() == null || request.getName().isEmpty() || !ValidationUtils.isAlphabetic(request.getName())){
            UserValidationErrorDTO error = new UserValidationErrorDTO(ValidationErrorConstants.INVALID_NAME);
            error.setInvalidValue(request.getName());
            error.setPropertyPath("RequestDTO#name");
            result.add(error);
        }

        if(request.getSurname() == null || request.getSurname().isEmpty()
                || !ValidationUtils.isAlphabetic(request.getSurname())){
            UserValidationErrorDTO error = new UserValidationErrorDTO(ValidationErrorConstants.INVALID_SURNAME);
            error.setInvalidValue(request.getSurname());
            error.setPropertyPath("RequestDTO#surname");
            result.add(error);
        }

        if(request.getEmail() == null || request.getEmail().isEmpty() || !ValidationUtils.isValidEmail(request.getEmail())){
            UserValidationErrorDTO error = new UserValidationErrorDTO(ValidationErrorConstants.INVALID_EMAIL);
            error.setInvalidValue(request.getEmail());
            error.setPropertyPath("RequestDTO#email");
            result.add(error);
        }

        if(request.getPolicyNumber() == null || request.getPolicyNumber().isEmpty()
                || !ValidationUtils.isAlphaNumeric(request.getPolicyNumber())){
            UserValidationErrorDTO error = new UserValidationErrorDTO(ValidationErrorConstants.INVALID_POLICY_NUMBER);
            error.setInvalidValue(request.getPolicyNumber());
            error.setPropertyPath("RequestDTO#policyNumber");
            result.add(error);
        }

        if(request.getMessage() == null || request.getMessage().isEmpty()){
            UserValidationErrorDTO error = new UserValidationErrorDTO(ValidationErrorConstants.EMPTY_MESSAGE);
            error.setInvalidValue(request.getMessage());
            error.setPropertyPath("RequestDTO#message");
            result.add(error);
        } else if(request.getMessage().length() > contactFormConfig.getMaxLength()){
            UserValidationErrorDTO error = new UserValidationErrorDTO(ValidationErrorConstants.MESSAGE_TOO_LONG);
            error.setInvalidValue(request.getMessage());
            error.setPropertyPath("RequestDTO#message");
            result.add(error);
        }

        return result;
    }

}
