package com.revature.utility;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
//        TODO 2 Test the connection
public class ConnectionUtilityTest {

    @Test
    public void getConnection() throws SQLException {
        ConnectionUtility.getConnection();
    }
}
