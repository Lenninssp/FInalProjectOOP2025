package com.tasky.app.dao;

import com.tasky.app.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    @BeforeEach
    public void setup() {
        UserDAO.createTable();
    }

    @Test
    public void testCreateAndGetUser() {
        User user = new User("unittestuser", "unit@gmail.com", "pass123");
        UserDAO.createUser(user);

        assertTrue(user.getId() > 0, "User ID should be set after insertion");

        User retrieved = UserDAO.getUserById(user.getId());
        assertNotNull(retrieved, "User should be found in database, retreiving with ID");
        assertEquals(retrieved.getUsername(), user.getUsername());
        assertEquals(retrieved.getEmail(), user.getEmail());

        User retrieved2 = UserDAO.getUserByUsername(user.getUsername());
        assertNotNull(retrieved2, "User should be found in database, retrieving with username");
        assertEquals(retrieved2.getEmail(), user.getEmail());
        assertEquals(retrieved2.getUsername(), user.getUsername());
    }

    @Test
    public void testLoginWithCorrectCredentials() {

    }




}
