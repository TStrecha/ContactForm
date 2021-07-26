package eu.lundegaard.contactform.dto;

import lombok.Data;

@Data
public class RequestDTO {

    private Long requestType;
    private String policyNumber;
    private String name;
    private String surname;
    private String email;

    private String message;

}
