package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {

    @Test
    void testDatabaseMetaData() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            System.out.println("Database name: " + databaseMetaData.getDatabaseProductName());
            System.out.println("Database version: " + databaseMetaData.getDatabaseProductVersion());

            ResultSet resultSet = databaseMetaData.getTables("java_database", null, null, null);
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);
            }

            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testParameterMetaData() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();

            String sql = "INSERT INTO comments (email, comment) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

            System.out.println(parameterMetaData.getParameterCount());
            //System.out.println(parameterMetaData.getParameterTypeName(1)); // this method is not support for mysql driver

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testResultSetMetaData() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sample_time");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            System.out.println(resultSetMetaData.getColumnCount());

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.println("\ncolumn name: " + resultSetMetaData.getColumnName(i));
                System.out.println("column type: " + resultSetMetaData.getColumnType(i));
                System.out.println("column type name: " + resultSetMetaData.getColumnTypeName(i));

                if (resultSetMetaData.getColumnType(i) == Types.INTEGER) {
                    System.out.println("THIS IS INTEGER");
                }
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
