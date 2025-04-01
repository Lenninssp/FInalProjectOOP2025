import java.sql.*;

import static java.lang.System.out;

public class UserDAO {
    public static void createTable () {
        try (Connection conn = DataBaseConnector.connect()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL,
                    email VARCHAR(100),
                    password VARCHAR(100)
                    );
                    """;
            assert conn != null;
            conn.createStatement().execute(sql);
            out.println("Table users created successfully");
        } catch (SQLException e) {
            out.println("Error creating table " + e.getMessage());
        }
    }
    public static void createUser(User user) {
        User existing = getUserByUsername(user.getUsername());
        if(existing != null){
            out.println("El usuario ya existe");
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
                stmt.setString(3, user.getPassword());

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
            out.println("Error creating user: " + e.getMessage());
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
            out.println("Error reading user: " + e.getMessage());
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
            out.println("Error reading user: " + e.getMessage());
        }

        return null;
    }

}
