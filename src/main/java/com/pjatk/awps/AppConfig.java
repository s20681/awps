package com.pjatk.awps;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("development")
//@PropertySource("application-development.properties")
public class AppConfig {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-development.properties");
    }
}