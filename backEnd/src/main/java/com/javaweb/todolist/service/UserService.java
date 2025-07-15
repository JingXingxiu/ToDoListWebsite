package com.javaweb.todolist.service;

import com.javaweb.todolist.controller.dto.setting.SystemSettingRequest;
import com.javaweb.todolist.controller.dto.setting.SystemSettingResponse;
import com.javaweb.todolist.controller.exception.setting.*;
import com.javaweb.todolist.dao.UserDAO;
import com.javaweb.todolist.dao.implement.UserMySQLDAO;
import com.javaweb.todolist.controller.dto.register.RegisterRequest;
import com.javaweb.todolist.controller.dto.register.RegisterResponse;
import com.javaweb.todolist.controller.exception.register.RegisterRepeatedCountException;
import com.javaweb.todolist.model.User;

public class UserService {
    private final UserDAO userMySQL=new UserMySQLDAO();

    public RegisterResponse createUser(RegisterRequest registerRequest){
        if(userMySQL.findExistUsername(registerRequest.getUsername())) throw new RegisterRepeatedCountException();
        userMySQL.createUser(new User(registerRequest.getUsername(),registerRequest.getPassword()));
        return new RegisterResponse(200);
    }

    public boolean findExistUser(String userID){
        return userMySQL.findExistUserByID(userID);
    }


    public SystemSettingResponse updateUser(String userID, SystemSettingRequest systemSettingRequest) {
        String type=systemSettingRequest.getType(),information=systemSettingRequest.getChange();
        /// 采取硬编码，可以用反射来调用数据库，但是有点没必要了！
        switch (type) {
            case "username" -> {
                if(userMySQL.findExistUsername(information)) throw new RepeatedUsernameException();
                if(!userMySQL.updateUserNameByID(userID, information)) throw new FailedUpdateUsernameException();
            }
            case "password" -> {
                if(!userMySQL.updatePasswordByID(userID, information)) throw new FailedUpdatePasswordException();
            }
            case "email" -> {
                if(!userMySQL.setUserEmailByID(userID, information)) throw new FailedUpdateEmailException();
            }
            default -> throw new UnknowTypeUpdateUserException();
        }
        return new SystemSettingResponse(200);
    }
}
