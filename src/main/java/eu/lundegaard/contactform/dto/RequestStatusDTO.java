package eu.lundegaard.contactform.dto;

import eu.lundegaard.contactform.constant.StatusEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestStatusDTO {

    private StatusEnum status = StatusEnum.NONE;

    private List<UserValidationErrorDTO> errors = new ArrayList<>();

}
