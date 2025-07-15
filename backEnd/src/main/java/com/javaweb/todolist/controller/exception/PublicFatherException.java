package com.javaweb.todolist.controller.exception;
/// 利用异常处理实现返回不同的Response（登录处理结果）
/// 此Exception是父类，将要被继承（如密码错误，如账号不存在。两个是不同的异常）
///
/// 首先在Controller用Jackson解析出LoginRequest的DTO，然后传给AuthService实例的方法，作为参数进行登录校验。
/// 其中成功就返回LoginResponse的dto，然后直接json写入response，
/// 失败的话抛出一个AuthPasswordErrorException，catch后，得到一个LoginError，然后直接json写入response，
/// 至此程序结束
public abstract class PublicFatherException extends RuntimeException{
    private final String module;///错误类型：auth 验证 register 注册 todolist相关等等
    private final String errorMessage;///错误信息
    private final int status;///状态码

    public PublicFatherException(String module, String errorMessage, int status){
        super(errorMessage);
        this.module=module;
        this.errorMessage=errorMessage;
        this.status=status;
    }

    public String getModule() {
        return module;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
