package org.example.management.service;

import org.example.management.model.entity.User;
import org.example.management.model.enums.Role;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTest {

    @Test
    void testUserCreation() {
        // Arrange & Act
        User user = User.builder()
                .id(UUID.randomUUID())
                .email("test@example.com")
                .password("password")
                .role(Role.ROLE_USER)
                .build();

        // Assert
        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ROLE_USER, user.getRole());
        assertTrue(user.isEnabled());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testUserUsername() {
        // Arrange
        User user = User.builder()
                .email("test@example.com")
                .build();

        // Act & Assert
        assertEquals("test@example.com", user.getUsername());
    }

    @Test
    void testUserAuthorities() {
        // Arrange
        User user = User.builder()
                .role(Role.ROLE_ADMIN)
                .build();

        // Act & Assert
        assertNotNull(user.getAuthorities());
        assertEquals(1, user.getAuthorities().size());
        assertTrue(user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")));
    }
} 