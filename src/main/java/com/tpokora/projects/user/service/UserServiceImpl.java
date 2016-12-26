package com.tpokora.projects.user.service;

import com.tpokora.projects.common.utils.SecurityUtilities;
import com.tpokora.projects.user.dao.UserRepository;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.nullobjects.NullUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tomek on 2016-01-16.
 */
@Service("userService")
@PropertySource("classpath:properties/${env:dev}.properties")
public class UserServiceImpl implements UserService {

    @Autowired
    private Environment env;

    @Resource
    private UserRepository userRepo;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        try {
            User user = userRepo.findOne(id);
        } catch(IndexOutOfBoundsException e) {
            return new NullUser();
        } catch(UnexpectedRollbackException e) {
            return new NullUser();
        }

        return userRepo.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        try {
            User user = userRepo.findByUsername(username);
        } catch (IndexOutOfBoundsException e) {
            return new NullUser();
        } catch(UnexpectedRollbackException e) {
            return new NullUser();
        }

        return userRepo.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        try {
            User user = userRepo.findByEmail(email);
        } catch (IndexOutOfBoundsException e) {
            return new NullUser();
        } catch(UnexpectedRollbackException e) {
            return new NullUser();
        }

        return userRepo.findByEmail(email);
    }

    @Override
    @Transactional
    public User createOrUpdateUser(User user) {
        User userToSave = user;
        if (user.getId() != null) {
            userToSave = userRepo.findOne(user.getId());
            userToSave.setUsername(user.getUsername());
            userToSave.setPassword(user.getPassword());
            userToSave.setEmail(user.getEmail());
        }

        userToSave.setPassword(SecurityUtilities.hashingPassword(userToSave.getPassword()));
        userRepo.saveAndFlush(userToSave);
        return userRepo.findByUsername(userToSave.getUsername());
    }

    @Override
    @Transactional
    public void removeUserById(int id) {
        userRepo.delete(id);
    }
}
