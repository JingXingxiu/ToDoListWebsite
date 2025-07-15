package com.javaweb.todolist.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接池管理工具类，基于 HikariCP 实现。
 * 负责初始化连接池并提供获取连接的方法。
 */
public class DBConnectionUtil {

    // 单例连接池实例，全局只初始化一次
    private static final HikariDataSource dataSource;

    static {
        // 创建 Hikari 配置对象
        HikariConfig config = new HikariConfig("/config.properties");

///        // 基本连接信息（建议你放在 config.properties 中再读取，这里为了简洁写死）
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/your_database?useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
//        config.setUsername("your_username");
//        config.setPassword("your_password");
//
///        // ============ 常用配置项 ============
//
///       // 连接池中最大连接数（同时最多有多少个连接）
//        config.setMaximumPoolSize(10);
//
///        // 最小空闲连接数（闲置时保持多少个可用连接，默认和最大连接数一样）
//        config.setMinimumIdle(2);
//
///        // 空闲连接最大存活时间，超过时间未被使用就会被回收（毫秒）
//        config.setIdleTimeout(60000); // 60 秒
//
///        // 最长等待连接时间（连接池满了还没可用连接时，客户端最多等待多久）
//        config.setConnectionTimeout(30000); // 30 秒，建议不要太长
//
///        // 每个连接的最大存活时间（超过就强制重建，防止连接泄漏）
//        config.setMaxLifetime(300000); // 5 分钟
//
///        // 检测连接泄漏（调试用）：连接获取后多久未关闭则认为泄漏
//        config.setLeakDetectionThreshold(15000); // 15 秒
//
///        // 是否自动提交事务（你也可以手动控制）
//        config.setAutoCommit(true);



        /// 初始化连接池
        dataSource = new HikariDataSource(config);
    }

    /**
     * 获取数据库连接对象，使用连接池中的连接。
     *
     * @return Connection 数据库连接
     * @throws SQLException 获取失败时抛出异常
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 关闭连接池（一般项目结束或系统关闭时调用）
     */
    public static void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
