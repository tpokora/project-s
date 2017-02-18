package com.tpokora.projects.user.service;

import com.tpokora.projects.common.AbstractTest;
import com.tpokora.projects.common.utils.SecurityUtilities;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.UserPassword;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by pokor on 13.11.2016.
 */
public class UserPasswordServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPasswordService userPasswordService;

    private static final String TEMPPASSWORD = SecurityUtilities.hashingPassword(SecurityUtilities.randomString(SecurityUtilities.CHARSET_AZ_09, SecurityUtilities.PASSWORD_LENGTH));

    private User user;

    @Before
    public void setup() {
        user = new User(null, "testUserResetPassword", "test", "testUserResetPassword@test.com", "ROLE_USER");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void userPasswordChanged_success() {
        user = userService.createOrUpdateUser(user);
        UserPassword userOldPassword = userPasswordService.getUserById(user.getId());
        String oldPassword = userOldPassword.getPassword();
        UserPassword userNewPassword = new UserPassword(user.getId(), TEMPPASSWORD);
        UserPassword userNewPasswordAfterUpdate = userPasswordService.updateUserPassword(userNewPassword);
        Assert.assertFalse("Password the same. OLD: " + userOldPassword.getPassword() + ", NEW" + userNewPassword.getPassword(),
                userNewPasswordAfterUpdate.getPassword().equals(oldPassword));
    }
}
