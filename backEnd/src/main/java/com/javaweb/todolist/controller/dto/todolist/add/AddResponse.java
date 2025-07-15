package com.javaweb.todolist.controller.dto.todolist.add;

import com.javaweb.todolist.controller.dto.PublicResponse;

public class AddResponse extends PublicResponse {
    private final String uniqueID;
    public AddResponse(int status,String uniqueID) {
        super(status);
        this.uniqueID=uniqueID;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}
