package com.javaweb.todolist.controller.exception.setting;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class FailedUpdateEmailException extends PublicFatherException {
    public FailedUpdateEmailException() {
        super("setting", "邮箱更新失败", 400);
    }
}
