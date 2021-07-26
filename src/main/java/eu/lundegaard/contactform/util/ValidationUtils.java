package eu.lundegaard.contactform.util;

public class ValidationUtils {

    public static boolean isValidEmail(String text){
        return text.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean isAlphabetic(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }
}
