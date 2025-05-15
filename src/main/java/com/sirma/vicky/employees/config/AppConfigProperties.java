package com.sirma.vicky.employees.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfigProperties {
    private List<String> supportedDateFormats;

    public List<String> getSupportedDateFormats() {
        return supportedDateFormats;
    }

    public void setSupportedDateFormats(List<String> supportedDateFormats) {
        this.supportedDateFormats = supportedDateFormats;
    }
    @PostConstruct
    public void logFormats() {
        System.out.println("Supported formats: " + supportedDateFormats);
    }

}
