package com.javaweb.todolist.controller.api;


import com.javaweb.todolist.controller.dto.ErrorResponse;
import com.javaweb.todolist.controller.dto.register.RegisterRequest;
import com.javaweb.todolist.controller.dto.register.RegisterResponse;
import com.javaweb.todolist.controller.exception.register.RegisterRepeatedCountException;
import com.javaweb.todolist.service.UserService;
import com.javaweb.todolist.util.JacksonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/user/register")
public class UserRegisterController extends HttpServlet {
    private final UserService userService=new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterRequest registerRequest=JacksonUtil.parseRequest(req,RegisterRequest.class);
        try{
            RegisterResponse registerResponse =userService.createUser(registerRequest);
            JacksonUtil.writeJson(resp,registerResponse);
        }catch(RegisterRepeatedCountException e){
            ErrorResponse errorResponse=new ErrorResponse(e.getModule(),e.getErrorMessage(),e.getStatus());
            JacksonUtil.writeJson(resp,errorResponse);
        }

    }
}
