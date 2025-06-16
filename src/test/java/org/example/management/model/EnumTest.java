package org.example.management.model;

import org.example.management.model.enums.Role;
import org.example.management.model.enums.TaskPriority;
import org.example.management.model.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumTest {

    @Test
    void testRoleEnum() {
        assertEquals(Role.ROLE_USER, Role.valueOf("ROLE_USER"));
        assertEquals(Role.ROLE_ADMIN, Role.valueOf("ROLE_ADMIN"));
        assertEquals(2, Role.values().length);
    }

    @Test
    void testTaskPriorityEnum() {
        assertEquals(TaskPriority.LOW, TaskPriority.valueOf("LOW"));
        assertEquals(TaskPriority.MEDIUM, TaskPriority.valueOf("MEDIUM"));
        assertEquals(TaskPriority.HIGH, TaskPriority.valueOf("HIGH"));
        assertEquals(3, TaskPriority.values().length);
    }

    @Test
    void testTaskStatusEnum() {
        assertEquals(TaskStatus.PENDING, TaskStatus.valueOf("PENDING"));
        assertEquals(TaskStatus.IN_PROGRESS, TaskStatus.valueOf("IN_PROGRESS"));
        assertEquals(TaskStatus.COMPLETED, TaskStatus.valueOf("COMPLETED"));
        assertEquals(3, TaskStatus.values().length);
    }
} 