package com.tasky.app.dao;

import com.tasky.app.util.DataBaseConnector;
import com.tasky.app.model.User;
import com.tasky.app.util.PasswordEncrypter;

import javax.xml.crypto.Data;
import java.sql.*;

import static java.lang.System.out;

public class UserDAO {
    public static void createUser(User user) {
        User existing = getUserByUsername(user.getUsername());
        if(existing != null){
            out.println("The user already exists");
            return;
        }
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DataBaseConnector.connect()
        ) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ){
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                String hashedPassword = PasswordEncrypter.hashPassword(user.getPassword());
                stmt.setString(3, hashedPassword);

                int rows = stmt.executeUpdate();

                if(rows > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if(generatedKeys.next()){
                        int id = generatedKeys.getInt(1);
                        user.setId(id);
                        out.println("Created user with id: " + id);
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Error creating user: " + e.getMessage());
        }
    }

    public static User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DataBaseConnector.connect()) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    return new User(id, username, email, password);
                }
            }
        }
        catch(SQLException e) {
            throw new RuntimeException("Error reading user: " + e.getMessage());
        }
        return null;
    }

    public static User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DataBaseConnector.connect()) {
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String foundUsername = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    return new User(id, foundUsername, email, password);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading user: " + e.getMessage());
        }

        return null;
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        String hashedPassword = PasswordEncrypter.hashPassword(password);
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DataBaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                return new User(id, username, email, password);
            }

        } catch (SQLException e) {
            throw new RuntimeException("❌ Error during login: " + e.getMessage());
        }
        return null;
    }

    public static boolean deleteUser(Integer id, String username) {
        if (id == null && username == null) {
            throw new IllegalArgumentException("Either id or username must be provided");
        }

        String sql = (id != null) ? "DELETE FROM users WHERE id = ?" : "DELETE FROM users WHERE username = ?";

        try (Connection conn = DataBaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (id != null) {
                stmt.setInt(1, id);
            } else {
                stmt.setString(1, username);
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

}
