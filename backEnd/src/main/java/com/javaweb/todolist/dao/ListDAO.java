package com.javaweb.todolist.dao;

import com.javaweb.todolist.model.ToDoList;

import java.sql.SQLException;
import java.util.List;

public interface ListDAO {

    /// 增
    public boolean addTodoListByID(ToDoList toDoList);

    /// 删
    public boolean deleteTodoListByID(String userID,String uniqueID);

    /// 改
    public boolean updateTodoListByID(ToDoList toDoList);

    /// 查
    public List<ToDoList> getAllUserTodoList(String userID);

}
