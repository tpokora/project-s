package com.tpokora.projects.user.service;

import com.tpokora.projects.user.dao.UserRepository;
import com.tpokora.projects.user.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pokor on 29.10.2016.
 */
@Service("userSetPasswordService")
public class UserSetPasswordServiceImpl implements UserSetPasswordService {

    @Resource
    private UserRepository userRepo;

    @Override
    public User updateUserPassword(User user) {
        User userPasswordToUpdate = user;
        userRepo.saveAndFlush(userPasswordToUpdate);
        return userRepo.findByUsername(userPasswordToUpdate.getUsername());
    }
}
