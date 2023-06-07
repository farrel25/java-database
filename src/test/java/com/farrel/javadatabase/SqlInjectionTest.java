package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {

    @Test
    void testSqlInjection() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

//            String username = "admin";
//            String password = "p@ssw0rd";
            String username = "admin'; #";
            String password = "salah";

            String sql = "SELECT a.* " +
                    "FROM admin AS a " +
                    "WHERE a.username= '" + username + "' AND a.password= '" + password + "'";

            System.out.println(sql);
            // SELECT a.* FROM admin AS a WHERE a.username= 'admin'; #' AND a.password= 'salah'

            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                // login success
                System.out.println("Login success: " + resultSet.getString("username"));
            } else {
                // login failed
                System.out.println("Login failed");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
