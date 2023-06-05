package com.farrel.javadatabase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    void testHikariCP() {
        HikariConfig config = new HikariConfig();

        // connection configuration
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/java_database");
        config.setUsername("root");
        config.setPassword("");

        // connection pool configuration
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000); // 1s
        config.setMaxLifetime(10 * 60_000); // 10m

        // create connection pool
//        try {
//            HikariDataSource dataSource = new HikariDataSource(config);
//            Connection connection = dataSource.getConnection();
//            connection.close();
//            dataSource.close();
//        } catch (SQLException e) {
//            Assertions.fail(e);
//        }

        // create connection pool with try-resource
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            Connection connection = dataSource.getConnection();
            connection.close();
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testConnectionUtil() {
        try {
            Connection connection = ConnectionUtil.getDataSource().getConnection();
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
