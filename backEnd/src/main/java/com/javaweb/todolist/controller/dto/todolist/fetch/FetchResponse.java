package com.javaweb.todolist.controller.dto.todolist.fetch;
import com.javaweb.todolist.controller.dto.PublicResponse;
import com.javaweb.todolist.model.ToDoListShow;

import java.util.List;

public class FetchResponse extends PublicResponse {
    private final List<ToDoListShow> toDoLists;

    public FetchResponse(List<ToDoListShow> toDoLists, int status) {
        super(status);
        this.toDoLists = toDoLists;
    }

    public List<ToDoListShow> getToDoLists() {
        return toDoLists;
    }
}
