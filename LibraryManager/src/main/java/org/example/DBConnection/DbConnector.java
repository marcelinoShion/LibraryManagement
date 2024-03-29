package org.example.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/library";
        String user = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url,user,password);
    }
}
