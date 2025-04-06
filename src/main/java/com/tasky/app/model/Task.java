package com.tasky.app.model;

import com.tasky.app.util.DataBaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private int userId;
    private LocalDate dueDate;

    public Task(int id, String title, String description, boolean completed, int userId, LocalDate dueDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
        this.dueDate = dueDate;
    }

    public Task (String title, String description, boolean completed, int userId, LocalDate dueDate){
        this(0, title, description, completed, userId, dueDate);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDate getDueDate() {return dueDate;}
    public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}

    @Override
    public String toString() {
        return "com.tasky.app.model.Task{id=" + id + ", title='" + title + "', userId=" + userId + ", completed=" + completed + "dueDate: " + dueDate +"}";
    }
}
