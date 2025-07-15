package com.javaweb.todolist.controller.exception.system;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class DBUpdateTodoListFailedException extends PublicFatherException {
    public DBUpdateTodoListFailedException() {
        super("todolist", "数据库更新todolist失败", 500);
    }
}
