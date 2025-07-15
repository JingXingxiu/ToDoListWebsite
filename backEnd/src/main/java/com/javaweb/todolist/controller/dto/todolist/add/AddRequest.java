package com.javaweb.todolist.controller.dto.todolist.add;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddRequest {
    private final String title;
    private final String description;
    private final int completed;
    @JsonCreator
    public AddRequest(@JsonProperty("title") String title,@JsonProperty("description") String description,
                      @JsonProperty("completed") int completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
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
}
