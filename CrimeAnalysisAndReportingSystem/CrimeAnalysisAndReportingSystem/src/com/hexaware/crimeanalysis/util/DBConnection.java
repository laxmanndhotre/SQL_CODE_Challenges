package com.hexaware.crimeanalysis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Register MySQL JDBC Driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                // Get properties from PropertyUtil
                String hostname = PropertyUtil.getPropertyString("hostname");
                String dbname = PropertyUtil.getPropertyString("dbname");
                String username = PropertyUtil.getPropertyString("username");
                String password = PropertyUtil.getPropertyString("password");
                String port = PropertyUtil.getPropertyString("port");

                // Construct the connection string
                String connectionString = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;

                // Establish connection
                connection = DriverManager.getConnection(connectionString, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
