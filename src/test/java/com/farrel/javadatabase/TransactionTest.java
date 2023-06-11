package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    void testCommit() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO comments (email, comment) VALUES (?, ?)";

            String email = "putra@test.com";
            String comment = "this is comment";

            for (int i = 1; i <= 100; i ++) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, i + email);
                preparedStatement.setString(2, comment + i);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

            connection.commit();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testRollback() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO comments (email, comment) VALUES (?, ?)";

            String email = "putra@test.com";
            String comment = "this is comment";

            for (int i = 1; i <= 100; i ++) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.clearParameters();
                preparedStatement.setString(1, i + email);
                preparedStatement.setString(2, comment + i);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }

            connection.rollback();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
