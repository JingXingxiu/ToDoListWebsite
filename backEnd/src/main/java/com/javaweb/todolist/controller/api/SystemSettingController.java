package com.javaweb.todolist.controller.api;

import com.javaweb.todolist.controller.dto.ErrorResponse;
import com.javaweb.todolist.controller.dto.setting.SystemSettingRequest;
import com.javaweb.todolist.controller.dto.setting.SystemSettingResponse;
import com.javaweb.todolist.controller.exception.system.DBUpdateUserFailedException;
import com.javaweb.todolist.service.UserService;
import com.javaweb.todolist.util.JacksonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/api/todolist/setting")
public class SystemSettingController extends HttpServlet {
    private final UserService userService=new UserService();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SystemSettingRequest systemSettingRequest= JacksonUtil.parseRequest(req, SystemSettingRequest.class);
        String userID=(String) req.getAttribute("userID");
        try{
            SystemSettingResponse systemSettingResponse =userService.updateUser(userID,systemSettingRequest);
            JacksonUtil.writeJson(resp,systemSettingResponse);
        }catch(DBUpdateUserFailedException e){
            ErrorResponse errorResponse=new ErrorResponse(e.getModule(),e.getErrorMessage(),e.getStatus());
            JacksonUtil.writeJson(resp,errorResponse);
        }
    }
}
