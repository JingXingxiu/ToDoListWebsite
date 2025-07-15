package com.javaweb.todolist.controller.dto;

public class ErrorResponse {
    private final String model;
    private final String errorMessage;
    private final int status;

    public ErrorResponse(String model, String errorMessage, int status) {
        this.model = model;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatus() {
        return status;
    }
}
