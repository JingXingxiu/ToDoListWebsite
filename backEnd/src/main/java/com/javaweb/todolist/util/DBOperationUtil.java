package com.javaweb.todolist.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class DBOperationUtil {

    public static void fillParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException {
        for(int i=0;i<parameters.length;++i) preparedStatement.setObject(i+1,parameters[i]);
    }

    public static void query(Consumer<ResultSet> solve,String sql,Object... parameters){
        try(Connection connection=DBConnectionUtil.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            fillParameters(preparedStatement,parameters);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                solve.accept(resultSet);
            }
        }catch(SQLException e){
            System.out.println("查询失败：DBOperation：15行");
        }
    }

    public static boolean executeUpdate(String sql, Object... parameters) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            fillParameters(preparedStatement, parameters);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("更新操作失败：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean existsByIdOrName(String sql,String information){
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            fillParameters(stmt,information);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); /// 有结果则存在
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL查询是否存在User失败（根据ID）");
        }
    }
}
