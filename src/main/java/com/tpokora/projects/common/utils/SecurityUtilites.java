package com.tpokora.projects.common.utils;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Tomek on 2016-01-17.
 */
public class SecurityUtilites {

    private static final Logger logger = Logger.getLogger(SecurityUtilites.class);

    public static String hashingPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        return hashedPassword;
    }

    public static void main(String[] args) {
        if (args.length != 0) {
            System.out.println("Password '" + args[0] + "' hash: " + hashingPassword(args[0]));
        }
    }
}
