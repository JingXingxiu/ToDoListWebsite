package com.javaweb.todolist.dao.implement;

import com.javaweb.todolist.dao.UserDAO;
import com.javaweb.todolist.model.User;
import com.javaweb.todolist.util.DBOperationUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class UserMySQLDAO implements UserDAO {
    public UserMySQLDAO() {
    }

    /// 查   存在 find
    @Override
    public boolean findExistUserByID(String userID) {
        String sql = "SELECT 1 FROM user WHERE id = ? AND is_deleted = 0";
        return DBOperationUtil.existsByIdOrName(sql, userID);
    }

    @Override
    public boolean findExistUsername(String username) {
        String sql = "SELECT 1 FROM user WHERE username = ? AND is_deleted = 0";
        return DBOperationUtil.existsByIdOrName(sql, username);
    }

    /// 查   信息 get
    @Override
    public String getPasswordByUsername(String username) {
        return getPassword("username", username);
    }

    @Override
    public String getPasswordByUserID(String ID) {
        return getPassword("id", ID);
    }

    @Override
    public String getUserIDByUsername(String username) {
        final String[] userId = {null};

        String sql = "SELECT id FROM user WHERE username = ? AND is_deleted = 0";
        DBOperationUtil.query(resultSet -> {
            try {
                if (resultSet.next()) {
                    userId[0] = resultSet.getString("id");
                }
            } catch (SQLException e) {
                throw new RuntimeException("获取用户ID失败", e);
            }
        }, sql, username);

        return userId[0];
    }

    /// 增 create insert...
    @Override
    public boolean createUser(User user) {
        String sql = "INSERT INTO user (id, username, password, email) VALUES (?, ?, ?, ?)";
        return DBOperationUtil.executeUpdate(sql,
                user.getID(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

    /// 改 update
    @Override
    public boolean updateUserNameByID(String ID, String username) {
        String sql = "UPDATE user SET username = ? WHERE id = ? AND is_deleted = 0";
        return DBOperationUtil.executeUpdate(sql, username, ID);
    }

    @Override
    public boolean updatePasswordByID(String ID, String password) {
        String sql = "UPDATE user SET password = ? WHERE id = ? AND is_deleted = 0";
        return DBOperationUtil.executeUpdate(sql, password, ID);
    }

    @Override
    public boolean setUserEmailByID(String ID, String email) {
        String sql = "UPDATE user SET email = ? WHERE id = ? AND is_deleted = 0";
        return DBOperationUtil.executeUpdate(sql, email, ID);
    }

    /// 删 delete
    @Override
    public boolean deleteUserByID(String ID) {
        String sql = "UPDATE user SET is_deleted = 1 WHERE id = ?";
        return DBOperationUtil.executeUpdate(sql, ID);
    }

    // 辅助方法：获取密码
    private String getPassword(String field, String value) {
        final String[] password = {null};

        String sql = "SELECT password FROM user WHERE " + field + " = ? AND is_deleted = 0";
        /// 消费-生产者模式！
        DBOperationUtil.query(resultSet -> {
            try {
                if (resultSet.next()) {
                    password[0] = resultSet.getString("password");
                }
            } catch (SQLException e) {
                throw new RuntimeException("获取密码失败", e);
            }
        }, sql, value);

        return password[0];
    }
}

//USE todolist;
//CREATE TABLE IF NOT EXISTS user (
//        id VARCHAR(36) PRIMARY KEY COMMENT '用户ID(UUID)',
//username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
//password VARCHAR(100) NOT NULL COMMENT '密码',
//email VARCHAR(100) NULL COMMENT '邮箱',
//is_deleted TINYINT(1) DEFAULT 0 COMMENT '删除标记(0-未删除,1-已删除)',
//INDEX idx_username (username)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';