package com.tpokora.projects.user.service;

import com.tpokora.projects.user.dao.UserResetPasswordRepository;
import org.springframework.core.env.Environment;
import com.tpokora.projects.user.model.UserResetPassword;
import com.tpokora.projects.user.model.nullobjects.NullUserResetPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pokor on 27.10.2016.
 */
@Service("userResetPasswordService")
@PropertySource("classpath:properties/${env:dev}.properties")
public class UserResetPasswordServiceImpl implements UserResetPasswordService {

    @Autowired
    private Environment env;

    @Resource
    private UserResetPasswordRepository userResetPasswordRepo;

    @Override
    public List<UserResetPassword> getAllUserResetPasswordSessions() {
        return userResetPasswordRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResetPassword findBySessionId(String sessionID) {
        try {
            UserResetPassword userResetPassword = userResetPasswordRepo.findBySessionId(sessionID);
        } catch(IndexOutOfBoundsException e) {
            return new NullUserResetPassword();
        } catch(UnexpectedRollbackException e) {
            return new NullUserResetPassword();
        }

        return userResetPasswordRepo.findBySessionId(sessionID);
    }

    @Override
    public UserResetPassword createOrUpdateUserResetPassword(UserResetPassword userResetPassword) {
        UserResetPassword userResetPasswordToSave = userResetPassword;

        userResetPasswordRepo.saveAndFlush(userResetPasswordToSave);
        return userResetPasswordRepo.findBySessionId(userResetPasswordToSave.getSessionId());
    }

    @Override
    @Transactional
    public void removeUserResetPasswordBySessionID(String sessionID) {
        userResetPasswordRepo.delete(userResetPasswordRepo.findBySessionId(sessionID).getId());
    }
}
