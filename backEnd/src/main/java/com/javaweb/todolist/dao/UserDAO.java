package com.javaweb.todolist.dao;

import com.javaweb.todolist.model.User;

public interface UserDAO {
    /// 查   信息
    public String getPasswordByUserID(String ID);
    public String getPasswordByUsername(String username);
    public String getUserIDByUsername(String username);
    /// 查   存在
    public boolean findExistUsername(String username);
    public boolean findExistUserByID(String userID);


    /// 增
    public boolean createUser(User user);

    /// 改
    public boolean updateUserNameByID(String ID,String user);
    public boolean updatePasswordByID(String ID,String password);
    public boolean setUserEmailByID(String ID,String email);

    /// 删
    public boolean deleteUserByID(String ID);
}
