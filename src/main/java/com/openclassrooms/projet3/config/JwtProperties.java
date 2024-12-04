
package com.openclassrooms.projet3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtProperties {
    private String secret;
    private long expiration;
    private String issuer;
    private String audience;
    private String tokenPrefix = "Bearer ";
}
