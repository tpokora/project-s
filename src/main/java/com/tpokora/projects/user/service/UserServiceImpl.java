package com.tpokora.projects.user.service;

import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.nullobjects.NullUser;
import com.tpokora.projects.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tomek on 2016-01-16.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        try {
            User user = userDAO.getUserById(id);
        } catch(IndexOutOfBoundsException e) {
            return new NullUser();
        } catch(UnexpectedRollbackException e) {
            return new NullUser();
        }

        return userDAO.getUserById(id);
    }

    @Override
    public void createOrUpdateUser(User user) {
        userDAO.createOrUpdateUser(user);
    }

    @Override
    public void removeUserById(int id) {
        userDAO.removeUserById(id);
    }
}
