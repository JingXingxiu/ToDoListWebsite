package com.javaweb.todolist.controller.exception.register;

import com.javaweb.todolist.controller.exception.PublicFatherException;

public class RegisterRepeatedCountException extends PublicFatherException {
    public RegisterRepeatedCountException(){
        super("register","用户名已存在",400);
    }
}
