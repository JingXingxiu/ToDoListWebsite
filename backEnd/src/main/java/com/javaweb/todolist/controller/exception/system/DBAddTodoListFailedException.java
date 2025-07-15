package com.javaweb.todolist.controller.exception.system;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class DBAddTodoListFailedException extends PublicFatherException {
    public DBAddTodoListFailedException() {
        super("todolist", "请检查标题是否重复？", 500);
    }
}
