package com.tpokora.projects.user.service;

import com.tpokora.projects.user.dao.UserPasswordRepository;
import com.tpokora.projects.user.dao.UserRepository;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.UserPassword;
import com.tpokora.projects.user.model.nullobjects.NullUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by pokor on 29.10.2016.
 */
@Service("userSetPasswordService")
public class UserPasswordServiceImpl implements UserPasswordService {

    @Resource
    private UserPasswordRepository userPasswordRepo;

    @Override
    @Transactional(readOnly = true)
    public UserPassword getUserById(int id) {

        return userPasswordRepo.findOne(id);
    }
}
