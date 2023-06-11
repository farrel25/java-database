package com.farrel.javadatabase.repository;

import com.farrel.javadatabase.ConnectionUtil;
import com.farrel.javadatabase.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    @Override
    public void insert(Comment comment) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "INSERT INTO comments (email, comment) VALUES (?, ?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, comment.getEmail());
                preparedStatement.setString(2, comment.getComment());

                preparedStatement.executeUpdate();

                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        comment.setId(resultSet.getInt(1));
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment findById(Integer id) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments WHERE id = ?";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Comment(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        );

                    } else {
                        return null;
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAll() {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()) {
                String sql = "SELECT * FROM comments";

                try(ResultSet resultSet = statement.executeQuery(sql)) {
                    List<Comment> commentList = new ArrayList<>();

                    while (resultSet.next()) {
                        commentList.add(
                                new Comment(
                                        resultSet.getInt("id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("comment")
                                )
                        );
                    }

                    return commentList;

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findByEmail(String email) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM comments WHERE email LIKE ?";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "%" + email.trim() + "%");

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<Comment> commentList = new ArrayList<>();

                    while (resultSet.next()) {
                        commentList.add(
                                new Comment(
                                        resultSet.getInt("id"),
                                        resultSet.getString("email"),
                                        resultSet.getString("comment")
                                )
                        );
                    }

                    return commentList;

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
