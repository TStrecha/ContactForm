package eu.lundegaard.contactform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ContactFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactFormApplication.class, args);
    }

}
