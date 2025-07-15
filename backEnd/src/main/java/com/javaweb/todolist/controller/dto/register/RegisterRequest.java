package com.javaweb.todolist.controller.dto.register;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    private final String username;
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }
}

