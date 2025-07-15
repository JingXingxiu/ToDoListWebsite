package com.javaweb.todolist.controller.exception.filter;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class FilterInvalidTokenException extends PublicFatherException {
    public FilterInvalidTokenException() {
        super("token","Token不存在或格式不合法",401);
    }
}
