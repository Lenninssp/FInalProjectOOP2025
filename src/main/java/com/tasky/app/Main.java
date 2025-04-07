package com.tasky.app;

import com.tasky.app.util.DataBaseConnector;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Connection testConn = DataBaseConnector.connect();


    }
}