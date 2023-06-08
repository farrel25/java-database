package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {

    @Test
    void testAutoIncrement() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();

            String sql = "INSERT INTO comments (email, comment) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            String email = "putra@test.com";
            String comment = "this is comment";

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, comment);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("comments id: " + resultSet.getInt(1));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
