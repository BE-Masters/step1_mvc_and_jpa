package com.example.be_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.example")
@ConfigurationPropertiesScan
@EnableJpaAuditing
public class BeStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeStudyApplication.class, args);
    }

}
