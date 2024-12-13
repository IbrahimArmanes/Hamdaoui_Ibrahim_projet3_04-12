package com.openclassrooms.projet3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.openclassrooms.projet3.config.JwtProperties;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Projet3 API",
        version = "1.0",
        description = "API documentation for Projet3"
    )
)
@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class Projet3Application {

    public static void main(String[] args) {
        SpringApplication.run(Projet3Application.class, args);
    }
}
