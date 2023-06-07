package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {

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

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("customers.name");
                String email = resultSet.getString("email");

                System.out.println(
                        String.join(", ", "{\"id\": \"" + id + "\"", "\"name\": \"" + name + "\"", "\"email\": \"" + email + "\"}")
                );
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
