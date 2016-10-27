package com.tpokora.projects.user.service;

import com.tpokora.projects.user.model.UserResetPassword;

/**
 * Created by pokor on 27.10.2016.
 */
public interface UserResetPasswordService {
    public UserResetPassword findBySessionId(String sessionID);
    public UserResetPassword createOrUpdateUserResetPassword(UserResetPassword userResetPassword);
}
