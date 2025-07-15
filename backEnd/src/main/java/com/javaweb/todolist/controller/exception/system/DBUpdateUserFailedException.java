package com.javaweb.todolist.controller.exception.system;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class DBUpdateUserFailedException extends PublicFatherException {
    public DBUpdateUserFailedException() {
        super("setting", "数据库更新账号密码失败", 500);
    }
}
