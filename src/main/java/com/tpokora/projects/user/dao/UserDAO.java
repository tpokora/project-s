package com.tpokora.projects.user.dao;

import com.tpokora.projects.user.model.User;

import java.util.List;

/**
 * Created by Tomek on 2016-01-16.
 */
public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public void createOrUpdateUser(User user);
    public void removeUserById(int id);
}