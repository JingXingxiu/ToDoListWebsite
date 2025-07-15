package com.javaweb.todolist.controller.api.todolist;

import com.javaweb.todolist.controller.dto.ErrorResponse;
import com.javaweb.todolist.controller.dto.todolist.add.AddRequest;
import com.javaweb.todolist.controller.dto.todolist.add.AddResponse;
import com.javaweb.todolist.controller.exception.system.DBAddTodoListFailedException;
import com.javaweb.todolist.service.ToDoService;
import com.javaweb.todolist.util.JacksonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/api/todolist/add")
public class AddTodoController extends HttpServlet {
    private ToDoService toDoService=new ToDoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            AddRequest addRequest =JacksonUtil.parseRequest(req, AddRequest.class);
            String userID=(String) req.getAttribute("userID");
            AddResponse addResponse=toDoService.addToDoList(addRequest,userID);
            JacksonUtil.writeJson(resp,addResponse);
        }catch(DBAddTodoListFailedException e){
            ErrorResponse errorResponse=new ErrorResponse(e.getModule(),e.getErrorMessage(),e.getStatus());
            JacksonUtil.writeJson(resp,errorResponse);
        }
    }
}