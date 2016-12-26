package com.tpokora.projects.user.service;

import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.UserMailResponse;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Tomek on 2016-01-16.
 */
public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User getUserByUsername(String username);
    public User getUserByEmail(String email);
    public User createOrUpdateUser(User user);
    public void removeUserById(int id);
    public Future<UserMailResponse> sendUserRegistrationCompleteEmail(String email, String login);
}
