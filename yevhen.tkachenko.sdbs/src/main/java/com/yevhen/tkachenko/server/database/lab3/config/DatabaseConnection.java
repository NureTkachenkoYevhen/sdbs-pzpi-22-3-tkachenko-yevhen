package main.java.com.yevhen.tkachenko.server.database.lab3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/filmdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "asdfasdf";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}