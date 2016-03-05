package com.tpokora.projects.common.utils;

import com.tpokora.projects.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 05.03.2016.
 */
public class TestUtils {
    public static List<User> generateUsers(int amount) {
        List<User> usersList = new ArrayList<User>();
        for (int i = 1; i <= amount; i++) {
            usersList.add(new User(Long.valueOf(i), "user" + i, "pass", "email@email.com", "ROLE_USER"));
        }

        return usersList;
    }
}
