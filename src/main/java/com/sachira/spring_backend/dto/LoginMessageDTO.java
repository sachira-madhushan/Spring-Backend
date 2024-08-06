package com.sachira.spring_backend.dto;

public class LoginMessageDTO {
    private String email;
    private String message;
    private String token;
    public LoginMessageDTO(String email, String message, String token) {
        this.email = email;
        this.message = message;
        this.setToken(token);
    }


    public LoginMessageDTO(String email,String message) {
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
