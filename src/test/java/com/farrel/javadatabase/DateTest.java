package com.farrel.javadatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTest {

    @Test
    void testDate() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();

            String sql = "INSERT INTO sample_time (sample_date, sample_time, sample_timestamp) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            Date date = new Date(System.currentTimeMillis());
            Time time = new Time(System.currentTimeMillis());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            preparedStatement.setDate(1, date);
            preparedStatement.setTime(2, time);
            preparedStatement.setTimestamp(3, timestamp);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testDateQuery() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();

            String sql = "SELECT * FROM sample_time";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("\nDate: " + resultSet.getDate("sample_date"));
                System.out.println("Time: " + resultSet.getTime("sample_time"));
                System.out.println("Timestamp: " + resultSet.getTimestamp("sample_timestamp"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
