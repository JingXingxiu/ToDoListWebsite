package com.javaweb.todolist.controller.exception.auth;

import com.javaweb.todolist.controller.exception.PublicFatherException;

/// 密码错误时，new出LoginError
public class AuthPasswordErrorException extends PublicFatherException {
    public AuthPasswordErrorException() {
        super("auth","密码错误或账号不存在",401);///只有第一个问题，这里是安全设计
    }
}
