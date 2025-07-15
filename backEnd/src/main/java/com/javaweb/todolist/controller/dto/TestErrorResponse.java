package com.javaweb.todolist.controller.dto;

public class TestErrorResponse {
    private final String model;
    private final String errorMessage;
    private final int status;
    private final String testText;

    public TestErrorResponse(String model, String errorMessage, int status, String testText) {
        this.model = model;
        this.errorMessage = errorMessage;
        this.status = status;
        this.testText = testText;
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

    public String getTestText() {
        return testText;
    }
}
