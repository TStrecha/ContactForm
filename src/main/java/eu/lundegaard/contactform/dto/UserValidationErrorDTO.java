package eu.lundegaard.contactform.dto;

import eu.lundegaard.contactform.constant.ValidationErrorConstants;
import lombok.Data;

@Data
public class UserValidationErrorDTO {

    private String propertyPath;

    private Object invalidValue;

    private ValidationErrorConstants errorCode;

    private String translation;

    public UserValidationErrorDTO(ValidationErrorConstants errorCode){
        this.errorCode = errorCode;
        this.translation = errorCode.getTranslation();
    }

}
