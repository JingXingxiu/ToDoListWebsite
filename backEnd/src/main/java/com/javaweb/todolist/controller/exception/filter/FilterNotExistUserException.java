package com.javaweb.todolist.controller.exception.filter;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class FilterNotExistUserException extends PublicFatherException {
    public FilterNotExistUserException() {
        super("todolistFetch","用户ID不存在",404);
    }
}
