package com.javaweb.todolist.controller.exception.system;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class DBDeleteTodolistFailedException extends PublicFatherException {
    public DBDeleteTodolistFailedException() {
        super("todolist", "数据库删除todolist失败！", 500);
    }
}
