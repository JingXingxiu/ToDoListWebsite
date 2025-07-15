package com.javaweb.todolist.model;

import com.javaweb.todolist.util.GenerationUtil;

/// 使用逻辑删除
public class ToDoList {
    private String title;    ///任务标题
    private String description; ///描述任务
    private int completed;/// 0:未完成 1:已完成
    private final String userID;   ///属于哪个User的
    private final String uniqueID;

    public ToDoList(String title, String description, int completed, String userID, String uniqueID) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userID = userID;
        this.uniqueID = uniqueID;
    }

    public ToDoList(String title, String description, int completed , String userID) {
        this.title = title;
        this.description = description;
        this.userID = userID;
        this.completed = completed;
        uniqueID= GenerationUtil.generateUUID();
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getTitle() {
        return title;
    }

    public String getUserID() {
        return userID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
