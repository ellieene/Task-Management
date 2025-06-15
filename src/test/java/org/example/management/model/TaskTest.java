package org.example.management.model;

import org.example.management.model.entity.Task;
import org.example.management.model.entity.User;
import org.example.management.model.enums.TaskPriority;
import org.example.management.model.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskCreation() {
        // Arrange
        UUID id = UUID.randomUUID();
        User author = new User();
        author.setEmail("author@example.com");
        
        User executor = new User();
        executor.setEmail("executor@example.com");
        
        LocalDate now = LocalDate.now();

        // Act
        Task task = new Task();
        task.setId(id);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setPriority(TaskPriority.HIGH);
        task.setAuthor(author);
        task.setExecutor(executor);
        task.setCreatedAt(now);

        // Assert
        assertNotNull(task);
        assertEquals(id, task.getId());
        assertEquals("Test Task", task.getTitle());
        assertEquals("Test Description", task.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertEquals(TaskPriority.HIGH, task.getPriority());
        assertEquals(author, task.getAuthor());
        assertEquals(executor, task.getExecutor());
        assertEquals(now, task.getCreatedAt());
    }

    @Test
    void testTaskDefaultValues() {
        // Arrange & Act
        Task task = new Task();

        // Assert
        assertNotNull(task);
        assertEquals(TaskStatus.PENDING, task.getStatus());
        assertNotNull(task.getCreatedAt());
    }
} 