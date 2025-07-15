package com.javaweb.todolist.controller.exception.setting;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class FailedUpdatePasswordException extends PublicFatherException {
    public FailedUpdatePasswordException() {
        super("setting", "密码更新失败", 400);
    }
}
