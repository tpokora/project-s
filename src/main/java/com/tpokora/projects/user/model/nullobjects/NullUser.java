package com.tpokora.projects.user.model.nullobjects;

import com.tpokora.projects.user.model.User;

/**
 * Created by Tomek on 2016-01-17.
 */
public class NullUser extends User {

    @Override
    public String getName() {
        return "User is NULL";
    }
}
