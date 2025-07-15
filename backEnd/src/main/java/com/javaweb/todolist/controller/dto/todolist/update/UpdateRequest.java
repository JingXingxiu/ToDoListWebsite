package com.javaweb.todolist.controller.dto.todolist.update;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRequest {
    private final String title;
    private final String description;
    private final int completed;
    private final String uniqueID;
    @JsonCreator
    public UpdateRequest(@JsonProperty("title") String title,@JsonProperty("description") String description,
                         @JsonProperty("completed") int completed,@JsonProperty("uniqueID") String uniqueID) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.uniqueID = uniqueID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCompleted() {
        return completed;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}
