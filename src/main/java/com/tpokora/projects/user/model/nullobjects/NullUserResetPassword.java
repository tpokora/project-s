package com.tpokora.projects.user.model.nullobjects;

import com.tpokora.projects.user.model.UserResetPassword;

/**
 * Created by pokor on 27.10.2016.
 */
public class NullUserResetPassword extends UserResetPassword {

    @Override
    public String getSessionId() {
        return "UserResetPassword is NULL";
    }
}
