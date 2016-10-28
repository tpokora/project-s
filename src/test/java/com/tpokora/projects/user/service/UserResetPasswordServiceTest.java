package com.tpokora.projects.user.service;

import com.tpokora.projects.common.service.AbstractServiceTest;
import com.tpokora.projects.common.utils.SessionIdentifierGenerator;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.UserResetPassword;
import com.tpokora.projects.user.model.nullobjects.NullUserResetPassword;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pokor on 27.10.2016.
 */

public class UserResetPasswordServiceTest extends AbstractServiceTest {

    protected static final Logger logger = LoggerFactory.getLogger(UserResetPasswordServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserResetPasswordService userResetPasswordService;

    private User user;
    private UserResetPassword userResetPassword;

    private static final String SESSIONID = SessionIdentifierGenerator.generateSessionID();
    private static final String TEMPPASSWORD = SessionIdentifierGenerator.generateSessionID();
    private static final String OLDPASSWORD = SessionIdentifierGenerator.generateSessionID();

    @Before
    public void setup() {
        user = new User(null, "testUserResetPassword", "test", "testUserResetPassword@test.com", "ROLE_USER");
        userResetPassword = new UserResetPassword(SESSIONID, TEMPPASSWORD, OLDPASSWORD, new Date(), user);
    }

    @Test
    @Transactional
    public void getUserResetPassword_userResetPasswordNotExists() {
        userResetPassword = userResetPasswordService.findBySessionId(SESSIONID);
        Assert.assertEquals(true, userResetPassword == null);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getUserResetPassword_userResetPasswordExists() {
        userService.createOrUpdateUser(user);
        userResetPasswordService.createOrUpdateUserResetPassword(userResetPassword);

        Assert.assertEquals(SESSIONID, userResetPasswordService.findBySessionId(SESSIONID).getSessionId());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void removeUserResetPasswordremoveBySessionID_userResetPasswordNotExists() {
        userService.createOrUpdateUser(user);
        userResetPasswordService.createOrUpdateUserResetPassword(userResetPassword);

        Assert.assertEquals(SESSIONID, userResetPasswordService.findBySessionId(SESSIONID).getSessionId());

        userResetPasswordService.removeUserResetPasswordBySessionID(userResetPasswordService.findBySessionId(SESSIONID).getSessionId());

        Assert.assertEquals(true, userResetPasswordService.findBySessionId(SESSIONID) == null);
    }
}