package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class PrepareStatementTest {

    @Test
    void testPrepareStatement() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();

            String sql =
                    "SELECT a.* " +
                    "FROM admin AS a " +
                    "WHERE a.username = ? AND a.password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //String username = "admin";
            //String password = "p@ssw0rd";
            String username = "admin'; #";
            String password = "salah";

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // login success
                System.out.println("Login success: " + resultSet.getString("username"));
            } else {
                // login failed
                System.out.println("Login failed");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
