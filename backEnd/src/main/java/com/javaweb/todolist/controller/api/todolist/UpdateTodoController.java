package com.javaweb.todolist.controller.api.todolist;

import com.javaweb.todolist.controller.dto.ErrorResponse;
import com.javaweb.todolist.controller.dto.todolist.update.UpdateRequest;
import com.javaweb.todolist.controller.dto.todolist.update.UpdateResponse;
import com.javaweb.todolist.controller.exception.todolist.NotExistTodolistException;
import com.javaweb.todolist.service.ToDoService;
import com.javaweb.todolist.util.JacksonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/todolist/update")
public class UpdateTodoController extends HttpServlet {
    ToDoService toDoService=new ToDoService();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdateRequest updateRequest= JacksonUtil.parseRequest(req, UpdateRequest.class);
        String userID= (String) req.getAttribute("userID");
        try{
            UpdateResponse updateResponse=toDoService.updateToDoList(updateRequest,userID);
            JacksonUtil.writeJson(resp,updateResponse);
        }catch(NotExistTodolistException e){
            ErrorResponse errorResponse=new ErrorResponse(e.getModule(),e.getErrorMessage(),e.getStatus());
            JacksonUtil.writeJson(resp,errorResponse);
        }
    }
}
