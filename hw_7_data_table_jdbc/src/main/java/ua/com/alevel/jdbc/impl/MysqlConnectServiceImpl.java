package ua.com.alevel.jdbc.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.jdbc.DefaultDateBaseConnectSevice;
import ua.com.alevel.util.MysqlProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class MysqlConnectServiceImpl implements DefaultDateBaseConnectSevice {
    private MysqlProperties mysqlProperties;
    private Statement statement;
    private Connection connection;

    public MysqlConnectServiceImpl(MysqlProperties mysqlProperties) {
        this.mysqlProperties = mysqlProperties;
    }

    @Override
    public void connection() {

        try {

            Class.forName(mysqlProperties.getNameDriverClass());
            connection = DriverManager.getConnection(
                    mysqlProperties.getUrl(),
                    mysqlProperties.getUsername(),
                    mysqlProperties.getPassword()
            );
            statement = connection.createStatement();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Statement getStatement() {
        return statement;
    }
}
