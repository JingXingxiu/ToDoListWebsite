package com.javaweb.todolist.controller.exception.auth;

import com.javaweb.todolist.controller.exception.PublicFatherException;

/// 账号不存在异常处理
/// 要new一个LoginError，到时候写入Response
public class AuthNotAccountException extends PublicFatherException {
    public AuthNotAccountException() {
        super("auth","账号不存在或密码错误",404);///其实只有前者有问题，这里是安全设计
    }
}
