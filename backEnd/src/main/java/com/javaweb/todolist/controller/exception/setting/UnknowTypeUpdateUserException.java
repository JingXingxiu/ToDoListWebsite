package com.javaweb.todolist.controller.exception.setting;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class UnknowTypeUpdateUserException extends PublicFatherException {
    public UnknowTypeUpdateUserException() {
        super("user", "不存在该用户的类型修改更新", 404);
    }
}
