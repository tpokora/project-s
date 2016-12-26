package com.tpokora.projects.user.service;

import com.tpokora.projects.user.model.UserMailResponse;
import com.tpokora.projects.user.model.UserResetPassword;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by pokor on 27.10.2016.
 */
public interface UserResetPasswordService {
    public List<UserResetPassword> getAllUserResetPasswordSessions();
    public UserResetPassword findBySessionId(String sessionID);
    public UserResetPassword createOrUpdateUserResetPassword(UserResetPassword userResetPassword);
    public void removeUserResetPasswordBySessionID(String sessionID);
    public Future<UserMailResponse> sendResetPasswordEmail(String to, String newPassword, String sessionID);
}
