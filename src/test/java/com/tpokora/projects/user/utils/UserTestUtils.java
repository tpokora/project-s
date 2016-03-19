package com.tpokora.projects.user.utils;

import com.tpokora.projects.common.model.TableDetails;
import com.tpokora.projects.common.utils.TestUtils;
import com.tpokora.projects.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pokor on 05.03.2016.
 */
public class UserTestUtils extends TestUtils {

    public static List<User> generateUsers(int amount) {
        List<User> usersList = new ArrayList<User>();
        if (amount > 0) {
            for (int i = 1; i <= amount; i++) {
                usersList.add(new User(Long.valueOf(i), "user" + i, "pass", "email@email.com", "ROLE_USER"));
            }
        }

        return usersList;
    }

    public static TableDetails generateTableDetails() {
        TableDetails tableDetails = new TableDetails();
        tableDetails.setTableName("users");
        HashMap<String, String> tableColumnDetails = new HashMap<String, String>();
        tableColumnDetails.put("email", "string");
        tableColumnDetails.put("username", "string");
        tableColumnDetails.put("password", "string");
        tableColumnDetails.put("role", "string");
        return tableDetails;
    }
}
