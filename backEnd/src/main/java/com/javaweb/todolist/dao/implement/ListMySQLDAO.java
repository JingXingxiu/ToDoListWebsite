package com.javaweb.todolist.dao.implement;

import com.javaweb.todolist.dao.ListDAO;
import com.javaweb.todolist.model.ToDoList;
import com.javaweb.todolist.util.DBConnectionUtil;
import com.javaweb.todolist.util.DBOperationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListMySQLDAO implements ListDAO {
    private static final String TABLE_NAME = "todolist";

    @Override
    public List<ToDoList> getAllUserTodoList(String userID) {
        List<ToDoList> list = new ArrayList<>();
        String sql = "SELECT title, description, completed, userID, uniqueID " +
                "FROM " + TABLE_NAME + " " +
                "WHERE userID = ? AND is_deleted = 0";

        DBOperationUtil.query(resultSet -> {
            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException("遍历结果集出错");
                }
                list.add(createToDoListByResultSet(resultSet));
            }
        }, sql, userID);

        return list;
    }

    @Override
    public boolean addTodoListByID(ToDoList toDoList) {
        String sql = "INSERT INTO " + TABLE_NAME + " (uniqueID, userID, title, description, completed, is_deleted) " +
                "VALUES (?, ?, ?, ?, ?, 0)"; // 新增时直接设置 is_deleted=0

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            DBOperationUtil.fillParameters(preparedStatement,
                    toDoList.getUniqueID(),
                    toDoList.getUserID(),
                    toDoList.getTitle(),
                    toDoList.getDescription(),
                    toDoList.getCompleted());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteTodoListByID(String userID, String uniqueID) {
        String sql = "UPDATE " + TABLE_NAME + " SET is_deleted = 1 " +
                "WHERE userID = ? AND uniqueID = ? AND is_deleted = 0";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            DBOperationUtil.fillParameters(preparedStatement, userID, uniqueID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("删除待办事项失败：" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateTodoListByID(ToDoList toDoList) {
        String sql = "UPDATE " + TABLE_NAME + " " +
                "SET title = ?, description = ?, completed = ? " +
                "WHERE userID = ? AND uniqueID = ? AND is_deleted = 0";

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            DBOperationUtil.fillParameters(preparedStatement,
                    toDoList.getTitle(),
                    toDoList.getDescription(),
                    toDoList.getCompleted(),
                    toDoList.getUserID(),
                    toDoList.getUniqueID());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("更新待办事项失败：" + e.getMessage());
            return false;
        }
    }

    private ToDoList createToDoListByResultSet(ResultSet resultSet) {
        try {
            return new ToDoList(
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("completed"),
                    resultSet.getString("userID"),
                    resultSet.getString("uniqueID"));
        } catch (SQLException e) {
            throw new RuntimeException("创建ToDoList对象失败！", e);
        }
    }
}


//USE todolist;
//CREATE TABLE `todolist` (
//        `uniqueID` varchar(36) NOT NULL COMMENT 'UUID主键',
//        `userID` varchar(36) NOT NULL COMMENT '所属用户ID',
//        `title` varchar(100) NOT NULL COMMENT '待办事项标题',
//        `description` text COMMENT '待办事项描述',
//        `completed` tinyint NOT NULL DEFAULT '0' COMMENT '完成状态：0-未完成，1-已完成',
//        `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除，1-已删除',
//PRIMARY KEY (`uniqueID`),
//UNIQUE KEY `uk_userID_title` (`userID`, `title`) COMMENT '防止同一用户创建重复标题的任务',
//KEY `idx_userID_completed` (`userID`, `completed`) COMMENT '加速用户任务列表筛选'
//        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='待办事项表';