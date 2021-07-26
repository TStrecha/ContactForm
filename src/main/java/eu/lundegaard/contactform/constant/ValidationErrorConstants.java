package eu.lundegaard.contactform.constant;

import lombok.Getter;

public enum ValidationErrorConstants {

    INVALID_NAME("Name is invalid."),
    INVALID_SURNAME("Surname is invalid."),
    INVALID_EMAIL("Email is invalid."),
    INVALID_REQUEST_TYPE("Request type is invalid."),
    EMPTY_MESSAGE("Message is empty."),
    MESSAGE_TOO_LONG("Message is too long."),
    INVALID_POLICY_NUMBER("The policy number is invalid. It can not be empty and has to be alphanumeric.");

    @Getter
    private final String translation;

    ValidationErrorConstants(String translation){
        this.translation = translation;
    }

}
