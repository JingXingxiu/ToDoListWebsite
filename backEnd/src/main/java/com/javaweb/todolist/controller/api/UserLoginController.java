package com.javaweb.todolist.controller.api;

import com.javaweb.todolist.controller.dto.ErrorResponse;
import com.javaweb.todolist.controller.dto.login.LoginRequest;
import com.javaweb.todolist.controller.dto.login.LoginResponse;
import com.javaweb.todolist.controller.exception.auth.AuthNotAccountException;
import com.javaweb.todolist.controller.exception.auth.AuthPasswordErrorException;
import com.javaweb.todolist.service.AuthService;
import com.javaweb.todolist.util.JacksonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user/login")
public class UserLoginController extends HttpServlet {
    private final AuthService authService=new AuthService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginRequest loginRequest=JacksonUtil.parseRequest(req,LoginRequest.class);
/// 异常处理将在Controller里完成！
        try{
            LoginResponse loginResponse =authService.login(loginRequest);
            JacksonUtil.writeJson(resp,loginResponse);
        }catch(AuthNotAccountException | AuthPasswordErrorException e){
            ErrorResponse errorResponse=new ErrorResponse(e.getModule(), e.getErrorMessage(), e.getStatus());
            JacksonUtil.writeJson(resp,errorResponse);
        }
    }
}
