package com.javaweb.todolist.controller.exception.setting;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class FailedUpdateUsernameException extends PublicFatherException {
    public FailedUpdateUsernameException() {
        super("setting", "用户名更新失败", 404);
    }
}
