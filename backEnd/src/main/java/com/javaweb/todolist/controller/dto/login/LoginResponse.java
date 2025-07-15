package com.javaweb.todolist.controller.dto.login;

import com.javaweb.todolist.controller.dto.PublicResponse;

public class LoginResponse extends PublicResponse {
    private final String token;
    private final long expiresIn;
    public LoginResponse(String token, long expiresIn,int status) {
        super(status);
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
