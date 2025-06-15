package org.example.management.model;

import org.example.management.model.entity.Comment;
import org.example.management.model.entity.Task;
import org.example.management.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void testCommentCreation() {
        // Arrange
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setEmail("user@example.com");
        
        Task task = new Task();
        task.setTitle("Test Task");
        
        LocalDateTime now = LocalDateTime.now();

        // Act
        Comment comment = Comment.builder()
                .id(id)
                .content("Test comment content")
                .user(user)
                .task(task)
                .createdAt(now)
                .build();

        // Assert
        assertNotNull(comment);
        assertEquals(id, comment.getId());
        assertEquals("Test comment content", comment.getContent());
        assertEquals(user, comment.getUser());
        assertEquals(task, comment.getTask());
        assertEquals(now, comment.getCreatedAt());
    }

    @Test
    void testCommentDefaultValues() {
        // Arrange & Act
        Comment comment = Comment.builder()
                .content("Test comment")
                .build();

        // Assert
        assertNotNull(comment);
        assertEquals("Test comment", comment.getContent());
        assertNull(comment.getUser());
        assertNull(comment.getTask());
    }
} 