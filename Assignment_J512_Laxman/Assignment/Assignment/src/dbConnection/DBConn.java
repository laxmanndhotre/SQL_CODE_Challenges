package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public static Connection getConnection() {
        Connection conn = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechShop", "root", "0909");
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
