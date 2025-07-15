package com.javaweb.todolist.model;

import com.javaweb.todolist.util.GenerationUtil;

/// 使用逻辑删除
public class ToDoListShow {
    private String title;    ///任务标题
    private String description; ///描述任务
    private int completed;/// 0:未完成 1:已完成
    private final String uniqueID;

    public ToDoListShow(String title, String description, int completed, String uniqueID) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.uniqueID = uniqueID;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getTitle() {
        return title;
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
