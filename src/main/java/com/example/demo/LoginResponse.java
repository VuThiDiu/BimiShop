package com.example.demo;
import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String accessTokenAutomaticTaggingSystem;
    private String id;
    private String username;

    public LoginResponse() {
    }

    public LoginResponse(String accessToken, String accessTokenAutomaticTaggingSystem, String id, String username) {
        this.accessTokenAutomaticTaggingSystem = accessTokenAutomaticTaggingSystem;
        this.accessToken = accessToken;
        this.username = username;
        this.id=id;
    }
}
