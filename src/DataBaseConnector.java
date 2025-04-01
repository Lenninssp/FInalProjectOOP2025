import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.out;

public class DataBaseConnector {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            out.println("Coection to H2 successful");
            return conn;
        }
        catch( SQLException e) {
            out.println("Error al conectar " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        connect();
    }
}
