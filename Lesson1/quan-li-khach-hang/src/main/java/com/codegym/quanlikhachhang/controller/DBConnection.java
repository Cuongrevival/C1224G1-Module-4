package com.codegym.quanlikhachhang.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quanlikhach";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Doyoucopy123";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
