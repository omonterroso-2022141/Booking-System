package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Jwt {

    @Value("${security.jwt.secret-key}")
    private String key;
    @Value("${security.jwt.expiration}")
    private long expiration;

}
