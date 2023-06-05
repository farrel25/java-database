package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

//    static {
//        try {
//            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
//            DriverManager.registerDriver(mysqlDriver);
//        } catch (SQLException e) {
//            //throw new RuntimeException(e);
//            e.printStackTrace();
//        }
//    }

    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            System.out.println("Database Connection Success");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            Assertions.fail(e);
        }
    }

    @Test
    void testConnectionClose() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database";
        String username = "root";
        String password = "";

//        try {
//            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//            System.out.println("Database Connection Success");
//
//            connection.close();
//            System.out.println("Database Connection Closed");
//        } catch (SQLException e) {
//            Assertions.fail(e);
//        }

        // USING TRY WITH RESOURCE (AUTOMATICALLY CLOSE AFTER RUN THE RESOURCE
        // ALTHOUGH ERROR HAPPEN, THE TRY WITH RESOURCE WILL STILL CLOSE THE RESOURCE
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Database Connection Success");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
