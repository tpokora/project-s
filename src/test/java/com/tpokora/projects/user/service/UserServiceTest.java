package com.tpokora.projects.user.service;

import com.tpokora.projects.common.service.AbstractServiceTest;
import com.tpokora.projects.user.model.User;
import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pokor on 26.03.2016.
 */
public class UserServiceTest extends AbstractServiceTest {

    private static final Logger logger = Logger.getLogger(UserServiceTest.class);

    private User testUser;

    @Autowired
    UserService userService;

    @Before
    public void createTestUser() {
        testUser = new User();
        testUser.setUsername("AutoTestUser");
        testUser.setPassword("autotest");
        testUser.setEmail("autp_test_user@test.com");
        testUser.setRole("ROLE_USER");
    }
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
    @Rollback(true)
    public void getUserByUserName_AutoTestUser_userExists() {
        userService.createOrUpdateUser(testUser);

        User user = userService.getUserByUsername(testUser.getUsername());
        Assert.assertEquals(testUser.getUsername(), user.getUsername());
        Assert.assertEquals(testUser.getEmail(), user.getEmail());
        Assert.assertEquals(testUser.getRole(), user.getRole());
    }

    /**
     * Test if creating new user and then editing finished successfully
     */
    @Test
    @Transactional
    @Rollback(true)
    public void createOrUpdateUser_newUser_success() {
        userService.createOrUpdateUser(testUser);

        User addedUser = userService.getUserByUsername(testUser.getUsername());
        int testUserId = addedUser.getId();

        Assert.assertEquals(true, addedUser.getId() == testUserId);
        Assert.assertEquals(testUser.getUsername(), addedUser.getUsername());
        Assert.assertEquals(testUser.getEmail(), addedUser.getEmail());
        Assert.assertEquals(testUser.getRole(), addedUser.getRole());

        testUser.setUsername("editedUser");
        testUser.setEmail("editeduser@test.com");
        testUser.setRole("editedrole");

        userService.createOrUpdateUser(testUser);

        User updatedUser = userService.getUserById(testUserId);

        Assert.assertEquals(true, updatedUser.getId() == testUserId);
        Assert.assertEquals(testUser.getUsername(), updatedUser.getUsername());
        Assert.assertEquals(testUser.getEmail(), updatedUser.getEmail());
        Assert.assertEquals(testUser.getRole(), updatedUser.getRole());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void removeUserByName_testUser_success() {
        String removeUserString = "userToRemove";
        User userToRemove = new User();
        userToRemove.setUsername(removeUserString);
        userToRemove.setPassword(removeUserString);
        userToRemove.setEmail(removeUserString + "@" + removeUserString);
        userToRemove.setRole("ROLE_USER");

        userService.createOrUpdateUser(userToRemove);

        User user = userService.getUserByUsername(userToRemove.getUsername());

        Assert.assertEquals(true, user.getUsername().equals(userToRemove.getUsername()));

        userService.removeUserById(user.getId());

        Assert.assertNull(userService.getUserById(user.getId()));
    }
}
