package com.tpokora.projects.user.utils;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorTypes;
import com.tpokora.projects.common.utils.TestUtils;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import com.tpokora.projects.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 05.03.2016.
 */
public class UserTestUtils extends TestUtils {


    public static final ResponseEntity<RESTResponseWrapper> generateResponseWithUsersList(int amount) {
        restResponse.addContent("users", generateUsers(3));
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    public static final ResponseEntity<RESTResponseWrapper> generateResponseEmptyUsersList() {
        //userError.setError(ErrorTypes.USER_NOT_EXISTS);
        //restResponse.addError(userError);
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    private static List<User> generateUsers(int amount) {
        List<User> usersList = new ArrayList<User>();
        if (amount > 0) {
            for (int i = 1; i <= amount; i++) {
                usersList.add(new User(Long.valueOf(i), "user" + i, "pass", "email@email.com", "ROLE_USER"));
            }
        }

        return usersList;
    }
}
