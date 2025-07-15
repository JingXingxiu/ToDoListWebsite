package com.javaweb.todolist.controller.exception.todolist;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class NotExistTodolistException extends PublicFatherException {
    public NotExistTodolistException() {
        super("Todolist", "资源不存在!", 404);
    }
}
