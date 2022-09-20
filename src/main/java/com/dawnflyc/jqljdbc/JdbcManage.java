package com.dawnflyc.jqljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接管理器
 */
public class JdbcManage implements AutoCloseable {

    private Connection connection;

    /**
     * 实例化后自动创建，完成后自动关闭
     * @throws SQLException
     */
    public JdbcManage() throws SQLException {
        connection = DriverManager.getConnection(Config.getUrl(),Config.getUserName(),Config.getPassword());
    }

    static {
        try {
            Class.forName(Config.getDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * 自动关闭
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        if(connection != null){
            connection.close();
            connection = null;
        }
    }
}
