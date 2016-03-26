package com.tpokora.projects.user.service;

import com.tpokora.projects.common.service.AbstractServiceTest;
import com.tpokora.projects.user.model.User;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pokor on 26.03.2016.
 */
public class UserServiceTest extends AbstractServiceTest {

    private static final Logger logger = Logger.getLogger(UserServiceTest.class);

    @Autowired
    UserService userService;

    /**
     * Tests if UserRepository returns users list
     */
    @Test
    @Transactional
    public void getAllUsers_usersExists() {
        java.util.List<User> userList = userService.getAllUsers();
        Assert.assertEquals(false, userList.isEmpty());
    }

    @Test
    @Transactional
    public void getUserByUserName_TestUser_userExists() {
        User testUser = userService.getUserByUsername("TestUser");
        Assert.assertEquals("TestUser", testUser.getUsername());
        Assert.assertEquals("test_user@test.com", testUser.getEmail());
        Assert.assertEquals("ROLE_USER", testUser.getRole());
    }

    /**
     * Test if creating new user and then editing finished successfully
     */
    @Test
    @Transactional
    @Rollback(true)
    public void createOrUpdateUser_newUser_success() {
        User newUser = new User();
        newUser.setUsername("test_user");
        newUser.setPassword("test");
        newUser.setEmail("testuser@test.com");

        userService.createOrUpdateUser(newUser);

        User addedUser = userService.getUserByUsername(newUser.getUsername());
        int testUserId = addedUser.getId();

        Assert.assertEquals(true, addedUser.getId() == testUserId);
        Assert.assertEquals(newUser.getUsername(), addedUser.getUsername());
        Assert.assertEquals(newUser.getEmail(), addedUser.getEmail());
        Assert.assertEquals(newUser.getRole(), addedUser.getRole());

        newUser.setUsername("editedUser");
        newUser.setEmail("editeduser@test.com");
        newUser.setRole("editedrole");

        userService.createOrUpdateUser(newUser);

        User updatedUser = userService.getUserById(testUserId);

        Assert.assertEquals(true, updatedUser.getId() == testUserId);
        Assert.assertEquals(newUser.getUsername(), updatedUser.getUsername());
        Assert.assertEquals(newUser.getEmail(), updatedUser.getEmail());
        Assert.assertEquals(newUser.getRole(), updatedUser.getRole());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void removeUserById_16_success() {
        int id = 16;

        Assert.assertEquals(true, userService.getUserById(id).getId() == id);

        userService.removeUserById(16);

        Assert.assertNull(userService.getUserById(id));
    }

}
