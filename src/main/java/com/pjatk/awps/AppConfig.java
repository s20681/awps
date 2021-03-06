package com.pjatk.awps;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class AppConfig {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-development.properties");
    }
}