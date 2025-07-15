package com.javaweb.todolist.controller.dto.todolist.fetch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FetchRequest {
    private final String userID;
    @JsonCreator
    public FetchRequest(@JsonProperty("userID") String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}
