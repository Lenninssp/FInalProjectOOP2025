import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.System.out;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        try (Connection conn = DataBaseConnector.connect()) {
            String sql = """
                    CREATE TABLE USERS (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    usename VARCHAR(50) NOT NULL,
                    email VARCHAR(100),
                    password VARCHAR(100)
                    );
                    """;
            assert conn != null;
            conn.createStatement().execute(sql);
            out.println("Table users created successfully");
        } catch (SQLException e) {
            out.println("Error createing table " + e.getMessage());
        }
    }
}