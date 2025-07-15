package com.javaweb.todolist.controller.dto.setting;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemSettingRequest {
    private final String type;
    private final String change;

    public SystemSettingRequest(@JsonProperty() String type,@JsonProperty() String change) {
        this.type = type;
        this.change = change;
    }

    public String getType() {
        return type;
    }

    public String getChange() {
        return change;
    }
}
