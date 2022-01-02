package ua.com.alevel.jdbc;

import java.sql.Connection;
import java.sql.Statement;

public interface DefaultDateBaseConnectSevice {
    void connection();
    Connection getConnection();
    Statement getStatement();
}
