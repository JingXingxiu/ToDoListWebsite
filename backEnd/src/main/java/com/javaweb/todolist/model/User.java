package com.javaweb.todolist.model;
import com.javaweb.todolist.util.GenerationUtil;

import java.util.UUID;
public class User {
    private String username;
    private String password;
    private String ID;///唯一标识 UUID
    private String email;
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ID= GenerationUtil.generateUUID();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
