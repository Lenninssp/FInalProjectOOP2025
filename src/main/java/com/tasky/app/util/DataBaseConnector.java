package com.tasky.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.out;

public class DataBaseConnector {
    private static final String URL = "jdbc:h2:~/testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successful connection");
            return conn;
        } catch (Exception e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
            throw new RuntimeException("unhandled", e);
        }
    }
}