package eu.lundegaard.contactform;

import eu.lundegaard.contactform.util.ValidationUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtilsTest {

    @Test
    @Order(0)
    void email() {
        assertTrue(ValidationUtils.isValidEmail("test@gmail.com"));
        assertTrue(ValidationUtils.isValidEmail("test23@example.co.uk"));
        assertFalse(ValidationUtils.isValidEmail("@g.com"));
        assertFalse(ValidationUtils.isValidEmail("2r@"));
    }

    @Test
    @Order(1)
    void alphaNumeric() {
        assertTrue(ValidationUtils.isAlphaNumeric("123abc"));
        assertTrue(ValidationUtils.isAlphaNumeric("efg"));
        assertTrue(ValidationUtils.isAlphaNumeric("456"));
        assertFalse(ValidationUtils.isAlphaNumeric("12312@gh"));
        assertFalse(ValidationUtils.isAlphaNumeric("12'"));
        assertFalse(ValidationUtils.isAlphaNumeric("(fdsxc)"));
    }

    @Test
    @Order(2)
    void alphabetic() {
        assertTrue(ValidationUtils.isAlphabetic("test"));
        assertTrue(ValidationUtils.isAlphabetic("example"));
        assertFalse(ValidationUtils.isAlphabetic("@123F"));
        assertFalse(ValidationUtils.isAlphabetic("0xFFFFFF"));
        assertFalse(ValidationUtils.isAlphabetic("F!"));
    }

}
