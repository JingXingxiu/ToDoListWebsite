package com.javaweb.todolist.service;

import com.javaweb.todolist.dao.UserDAO;
import com.javaweb.todolist.dao.implement.UserMySQLDAO;
import com.javaweb.todolist.controller.dto.login.LoginRequest;
import com.javaweb.todolist.controller.dto.login.LoginResponse;
import com.javaweb.todolist.controller.exception.auth.AuthNotAccountException;
import com.javaweb.todolist.controller.exception.auth.AuthPasswordErrorException;
import com.javaweb.todolist.util.JwtUtil;

public class AuthService {
    private final UserDAO userMySQL=new UserMySQLDAO();
    /// private final tools to encode the password
    public AuthService() {}
    /// 正常情况下返回LoginResponse
    /// 密码错误和账号不存在分别抛出不同异常，然后直接在那里new LoginError和写入response
    /// 并且结束程序（返回null，然后在controller那里检验）
    public LoginResponse login(LoginRequest loginRequest) throws AuthNotAccountException, AuthPasswordErrorException {
        String username=loginRequest.getUsername(),password= loginRequest.getPassword();
        if(!userMySQL.findExistUsername(username)) throw new AuthNotAccountException();
        if(!password.equals(userMySQL.getPasswordByUsername(username))) throw new AuthPasswordErrorException();
        String userID=userMySQL.getUserIDByUsername(username);
        return new LoginResponse(JwtUtil.generateToken(userID),86400000,200);
    }
}
