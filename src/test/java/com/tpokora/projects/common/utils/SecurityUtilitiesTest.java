package com.tpokora.projects.common.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pokor on 01.11.2016.
 */
public class SecurityUtilitiesTest {

    @Test
    public void generatePassword_success() {
        String password = SecurityUtilities.randomString(SecurityUtilities.CHARSET_AZ_09, SecurityUtilities.PASSWORD_LENGTH);
        Assert.assertEquals(true, password.length() == SecurityUtilities.PASSWORD_LENGTH);
        String hashedPassword = SecurityUtilities.hashingPassword(password);
        Assert.assertEquals(true, password != hashedPassword);
        Assert.assertEquals(true, hashedPassword.length() == 60);
    }
}
