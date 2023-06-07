package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class BatchTest {

    @Test
    void testStatement() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO comments (email, comment) VALUES ('farrel@test.com', 'this is comment')";

            for (int i = 1; i <= 1000; i ++) {
                statement.addBatch(sql);
            }

            statement.executeBatch();

            statement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testPreparedStatement() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();

            String sql = "INSERT INTO comments (email, comment) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            String email = "putra@test.com";
            String comment = "this is comment";

            for (int i = 1; i <= 1000; i ++) {
                preparedStatement.clearParameters();
                preparedStatement.setString(1, i + email);
                preparedStatement.setString(2, comment + i);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
