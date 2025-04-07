package com.tasky.app.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnector {
    private static final String URL = "jdbc:postgresql://dpg-cvpjkg7gi27c73b6gmag-a.oregon-postgres.render.com:5432/finalprojectoop2025database";
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection connect() {
        try {
            System.out.println(USER + PASSWORD);
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successful connection");
            return conn;
        } catch (Exception e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
            throw new RuntimeException("unhandled", e);
        }
    }
}