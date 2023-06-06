package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class StatementTest {

    @Test
    void testCreateStatement() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testExecuteUpdate() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            String sql = """
                    INSERT INTO customers (id, name, email)
                    VALUES ('putra', 'Putra', 'putra@test.com')
                    """;

            int update = statement.executeUpdate(sql);
            System.out.println(update);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testExecuteDelete() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            String sql = """
                    DELETE FROM customers
                    """;

            int update = statement.executeUpdate(sql);
            System.out.println(update);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testExecuteQuery() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            String sql = """
                    SELECT * FROM customers
                    """;

            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
