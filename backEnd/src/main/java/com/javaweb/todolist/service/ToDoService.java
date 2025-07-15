package com.javaweb.todolist.service;

import com.javaweb.todolist.controller.dto.todolist.add.AddRequest;
import com.javaweb.todolist.controller.dto.todolist.add.AddResponse;
import com.javaweb.todolist.controller.dto.todolist.delete.DeleteRequest;
import com.javaweb.todolist.controller.dto.todolist.delete.DeleteResponse;
import com.javaweb.todolist.controller.dto.todolist.update.UpdateRequest;
import com.javaweb.todolist.controller.dto.todolist.update.UpdateResponse;
import com.javaweb.todolist.controller.exception.system.DBAddTodoListFailedException;
import com.javaweb.todolist.controller.exception.system.DBDeleteTodolistFailedException;
import com.javaweb.todolist.controller.exception.system.DBUpdateTodoListFailedException;
import com.javaweb.todolist.dao.ListDAO;
import com.javaweb.todolist.dao.implement.ListMySQLDAO;
import com.javaweb.todolist.controller.dto.todolist.fetch.FetchRequest;
import com.javaweb.todolist.controller.dto.todolist.fetch.FetchResponse;
import com.javaweb.todolist.model.ToDoList;
import com.javaweb.todolist.model.ToDoListShow;

import java.util.List;
import java.util.stream.Collectors;

public class ToDoService {
    private final ListDAO listMySQL=new ListMySQLDAO();

    public FetchResponse getToDoList(FetchRequest fetchRequest) {
        String userID = fetchRequest.getUserID();
        List<ToDoList> toDoLists = listMySQL.getAllUserTodoList(userID);
        // 转换逻辑
        List<ToDoListShow> showList = toDoLists.stream()
                .map(todo -> new ToDoListShow(
                        todo.getTitle(),
                        todo.getDescription(),
                        todo.getCompleted(),
                        todo.getUniqueID()
                ))
                .collect(Collectors.toList());
        return new FetchResponse(showList, 200);
    }

    public AddResponse addToDoList(AddRequest addRequest, String userID){
        /// 调用数据库，增加todolist给指定user增加todolist
        String title= addRequest.getTitle(),description= addRequest.getDescription();
        int completed= addRequest.getCompleted();
        ToDoList toDoList=new ToDoList(title,description,completed,userID);
        if(listMySQL.addTodoListByID(toDoList)) return new AddResponse(201,toDoList.getUniqueID());
        throw new DBAddTodoListFailedException();
    }

    public UpdateResponse updateToDoList(UpdateRequest updateRequest, String userID){
        String uniqueID=updateRequest.getUniqueID(),title= updateRequest.getTitle(),
                description= updateRequest.getDescription();
        int completed= updateRequest.getCompleted();
        if(listMySQL.updateTodoListByID(new ToDoList(title,description,completed,
                userID,uniqueID))) return new UpdateResponse(201);
        throw new DBUpdateTodoListFailedException();
    }

    public DeleteResponse deleteToDoList(DeleteRequest deleteRequest, String userID){
        String uniqueID= deleteRequest.getUniqueID();
        if(listMySQL.deleteTodoListByID(userID,uniqueID)) return new DeleteResponse(204);
        throw new DBDeleteTodolistFailedException();
    }
}
