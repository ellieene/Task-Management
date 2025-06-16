package org.example.management.model;

import org.example.management.model.entity.User;
import org.example.management.model.enums.Role;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserBuilder() {
        UUID id = UUID.randomUUID();
        User user = User.builder()
                .id(id)
                .email("test@example.com")
                .password("password123")
                .role(Role.ROLE_USER)
                .build();

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(Role.ROLE_USER, user.getRole());
    }

    @Test
    void testUserDetailsMethods() {
        User user = User.builder()
                .email("test@example.com")
                .role(Role.ROLE_ADMIN)
                .build();

        assertEquals("test@example.com", user.getUsername());
        assertTrue(user.isEnabled());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertNotNull(user.getAuthorities());
        assertEquals(1, user.getAuthorities().size());
    }

    @Test
    void testUserEquality() {
        UUID id = UUID.randomUUID();
        User user1 = User.builder()
                .id(id)
                .email("test@example.com")
                .password("password")
                .role(Role.ROLE_USER)
                .build();

        User user2 = User.builder()
                .id(id)
                .email("test@example.com")
                .password("password")
                .role(Role.ROLE_USER)
                .build();

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
} 