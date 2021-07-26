package eu.lundegaard.contactform.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "eu.lundegaard.contactform")
public class ContactFormConfig {
    @Value("${max_length:50000}")
    Long maxLength;

}
