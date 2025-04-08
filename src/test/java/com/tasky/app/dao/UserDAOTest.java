package com.tasky.app.dao;

import com.tasky.app.dao.UserDAO;
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
    public void testUserCreationAndRetreival() {

        String username = "unit_test_user";
        String email = "unit@example.com";
        String password = "test123";

        User user = new User(username, email, password);
        UserDAO.createUser(user);

        User fetchedUser = UserDAO.getUserById(user.getId());
        assertNotNull(fetchedUser);
        assertEquals(username, fetchedUser.getUsername());
        assertEquals(email, fetchedUser.getEmail());
        assertNotEquals(password, fetchedUser.getPassword());

    }

//    @Test
//    public void testCreateAndGetUser() {
//        User user = new User("unittestuser", )
//    }


}
