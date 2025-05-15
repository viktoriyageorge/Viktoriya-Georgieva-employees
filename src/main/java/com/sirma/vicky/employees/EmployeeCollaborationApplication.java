package com.sirma.vicky.employees;

import com.sirma.vicky.employees.config.AppConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfigProperties.class)
public class EmployeeCollaborationApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeCollaborationApplication.class, args);
    }
}
