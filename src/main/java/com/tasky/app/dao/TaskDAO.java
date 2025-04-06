package com.tasky.app.dao;

import com.tasky.app.util.DataBaseConnector;
import com.tasky.app.model.Task;

import java.sql.*;

import static java.lang.System.out;

public class TaskDAO {
    public static void createTaskTable() {
        try (Connection conn = DataBaseConnector.connect()) {
            String sql = """
            CREATE TABLE IF NOT EXISTS tasks (
                id INT AUTO_INCREMENT PRIMARY KEY,
                title VARCHAR(100) NOT NULL,
                description TEXT,
                completed BOOLEAN DEFAULT FALSE,
                user_id INT,
                FOREIGN KEY (user_id) REFERENCES users(id)
            );
        """;
            conn.createStatement().execute(sql);
            System.out.println("Tabla 'tasks' creada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error creando tabla 'tasks': " + e.getMessage());
        }
    }

    public static void createTask(Task task) {
        String sql = "INSERT INTO tasks (title, description, completed, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnector.connect()
        ) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ){
                stmt.setString(1, task.getTitle());
                stmt.setString(2, task.getDescription());
                stmt.setBoolean(3, task.isCompleted());
                stmt.setInt(4, task.getUserId());

                int rows = stmt.executeUpdate();

                if(rows > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if(generatedKeys.next()){
                        int id = generatedKeys.getInt(1);
                        task.setId(id);
                        out.println("Created task with id: " + id);
                    }
                }
            }
        }
        catch (SQLException e) {
            out.println("Error creating task: " + e.getMessage());
        }
    }

    public static Task getTaskById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = DataBaseConnector.connect()) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    boolean completed = rs.getBoolean("completed");
                    int userId = rs.getInt("userId");
                    return new Task( id,  title,  description,  completed,  userId);
                }
            }
        }
        catch(SQLException e) {
            out.println("Error reading task: " + e.getMessage());
        }
        return null;
    }
}
