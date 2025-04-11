package com.tasky.app.dao;

import com.tasky.app.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    @Test
    public void testCreateAndGetUser() {
        UserDAO.deleteUser(null, "unittestuser");
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
        UserDAO.deleteUser(null, "login_test_user");
        User user = new User("login_test_user", "login@test.com","mypassword");
        UserDAO.createUser(user);

        User fetched = UserDAO.getUserByUsernameAndPassword("login_test_user", "mypassword");
        assertNotNull(fetched, "Cannot find the user with that username and that password");
        assertEquals(user.getUsername(), fetched.getUsername());
    }

    @Test
    public void testLoginFailsWithWrongPassword() {
        String username = "login_fail_user";
        String email = "fail@test.com";
        String correctPassword = "secret123";
        String wrongPassword = "wrong123";

        UserDAO.deleteUser(null, username);
        User user = new User(username, email, correctPassword);
        UserDAO.createUser(user);

        User result = UserDAO.getUserByUsernameAndPassword(username, wrongPassword);
        assertNull(result, "Login should fail with incorrect password");
    }

    @Test
    public void testPreventDuplicateUserCreation() {
        String username = "dupe_user";
        UserDAO.deleteUser(null, username);

        User user1 = new User(username, "dupe1@test.com", "pass1");
        User user2 = new User(username, "dupe2@test.com", "pass2");

        UserDAO.createUser(user1);
        int firstId = user1.getId();

        UserDAO.createUser(user2);
        int secondId = user2.getId();

        assertEquals(firstId, secondId, "Second user creation should not overwrite the first");

        User result = UserDAO.getUserByUsername(username);
        assertNotNull(result);
        assertEquals("dupe1@test.com", result.getEmail());

        UserDAO.deleteUser(firstId, null);
    }

}
