package com.openclassrooms.projet3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.openclassrooms.projet3.config.JwtProperties;

@EnableConfigurationProperties(JwtProperties.class)
@SpringBootApplication
public class Projet3Application {

    public static void main(String[] args) {
        SpringApplication.run(Projet3Application.class, args);
    }

}
