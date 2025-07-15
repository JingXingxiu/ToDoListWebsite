package com.javaweb.todolist.controller.dto;

public class PublicResponse {
    private final int status;

    public PublicResponse(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
