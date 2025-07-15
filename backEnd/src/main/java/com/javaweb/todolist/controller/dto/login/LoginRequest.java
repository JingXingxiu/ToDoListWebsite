package com.javaweb.todolist.controller.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private final String username;
    private final String password;

    public LoginRequest(@JsonProperty("username") String username,@JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
