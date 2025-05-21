package Vehicle_rental_app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static String url = "jdbc:postgresql://localhost:5432/anka_rental";
    public static String username = "postgres";
    public static String password = "DSAzxc12345.";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
