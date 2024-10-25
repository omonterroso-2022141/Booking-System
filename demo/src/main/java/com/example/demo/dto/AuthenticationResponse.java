package com.example.demo.dto;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private String jwtToken;

    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}