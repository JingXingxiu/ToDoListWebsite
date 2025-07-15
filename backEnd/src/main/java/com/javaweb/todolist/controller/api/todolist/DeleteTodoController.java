package com.javaweb.todolist.controller.api.todolist;

import com.javaweb.todolist.controller.dto.ErrorResponse;
import com.javaweb.todolist.controller.dto.todolist.delete.DeleteRequest;
import com.javaweb.todolist.controller.dto.todolist.delete.DeleteResponse;
import com.javaweb.todolist.controller.exception.todolist.NotExistTodolistException;
import com.javaweb.todolist.service.ToDoService;
import com.javaweb.todolist.util.JacksonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/todolist/delete")
public class DeleteTodoController extends HttpServlet {
    ToDoService toDoService=new ToDoService();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeleteRequest deleteRequest= JacksonUtil.parseRequest(req, DeleteRequest.class);
        String userID= (String) req.getAttribute("userID");
        try{
            DeleteResponse deleteResponse=toDoService.deleteToDoList(deleteRequest,userID);
            JacksonUtil.writeJson(resp,deleteResponse);
        }catch(NotExistTodolistException e){
            ErrorResponse errorResponse=new ErrorResponse(e.getModule(),e.getErrorMessage(),e.getStatus());
            JacksonUtil.writeJson(resp,errorResponse);
        }
    }
}
