package com.javaweb.todolist.controller.exception.setting;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class RepeatedUsernameException extends PublicFatherException {
    public RepeatedUsernameException() {
        super("setting", "用户名重复!", 400);
    }
}
