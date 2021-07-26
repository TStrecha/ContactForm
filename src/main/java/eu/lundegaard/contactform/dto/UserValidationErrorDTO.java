package eu.lundegaard.contactform.dto;

import eu.lundegaard.contactform.constant.ValidationErrorConstants;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserValidationErrorDTO {

    private String propertyPath;

    private Object invalidValue;

    private ValidationErrorConstants errorCode;

}
