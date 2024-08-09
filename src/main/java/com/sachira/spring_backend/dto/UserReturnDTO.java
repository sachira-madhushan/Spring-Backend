package com.sachira.spring_backend.dto;

public class UserReturnDTO {
    private int id;
    private String username;

    public UserReturnDTO(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
