package com.javaweb.todolist.controller.api.todolist;
import com.javaweb.todolist.controller.dto.todolist.fetch.FetchRequest;
import com.javaweb.todolist.controller.dto.todolist.fetch.FetchResponse;
import com.javaweb.todolist.service.ToDoService;
import com.javaweb.todolist.util.JacksonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/todolist/fetch")
public class FetchTodosController extends HttpServlet {
    private final ToDoService toDoService=new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FetchRequest fetchRequest= new FetchRequest((String) req.getAttribute("userID"));
        try{
            FetchResponse fetchResponse =toDoService.getToDoList(fetchRequest);
            JacksonUtil.writeJson(resp,fetchResponse);
        }catch(Exception e){/// 暂时没想出来异常业务情况
            e.printStackTrace();
        }
    }
}
