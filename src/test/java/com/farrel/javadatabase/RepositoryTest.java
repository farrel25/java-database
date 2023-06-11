package com.farrel.javadatabase;

import com.farrel.javadatabase.entity.Comment;
import com.farrel.javadatabase.repository.CommentRepository;
import com.farrel.javadatabase.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("putra@domain.com", "This is Putra's comment");

        commentRepository.insert(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById(3305);
        Assertions.assertNotNull(comment);

        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment commentNotFound = commentRepository.findById(99999999);
        Assertions.assertNull(commentNotFound);
    }

    @Test
    void testFindAll() {
        List<Comment> commentList = commentRepository.findAll();
        System.out.println(commentList.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> commentList = commentRepository.findByEmail("far");
        System.out.println(commentList.size());
    }
}
