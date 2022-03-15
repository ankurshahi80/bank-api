package com.revature.utility;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO 3: Create utility package and the ConnectionUtility class to include a getConnection method to generate a Connection object
public class ConnectionUtility {

// TODO 4: Create a private constructor
    private ConnectionUtility() {
    }

    public static Connection getConnection() throws SQLException {
//        TODO 5: Register the postgres driver with JDBC DriverManager
        DriverManager.registerDriver(new Driver());

//        TODO 6: Get the database connection string (url), username, password
        String url = System.getenv("db_url");
        String username = System.getenv("db_username");
        String password = System.getenv("db_password");

//        TODO 7: Get the connection object from the DriverManager
        Connection connection = DriverManager.getConnection(url, username, password);

//        TODO 8: Return the connection object
        return connection;
    }

}
