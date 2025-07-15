package com.javaweb.todolist.controller.dto.todolist.delete;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteRequest {
    private final String uniqueID;
    @JsonCreator
    public DeleteRequest(@JsonProperty("uniqueID") String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}
