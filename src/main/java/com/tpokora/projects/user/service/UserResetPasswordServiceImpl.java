package com.tpokora.projects.user.service;

import com.tpokora.projects.user.dao.UserResetPasswordRepository;
import org.springframework.core.env.Environment;
import com.tpokora.projects.user.model.ResetPasswordMailResponse;
import com.tpokora.projects.user.model.UserResetPassword;
import com.tpokora.projects.user.model.nullobjects.NullUserResetPassword;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

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

    @Override
    public Future<ResetPasswordMailResponse> sendResetPasswordEmail(String to, String newPassword, String sessionID) {
        HashMap<String, Object> content = new HashMap<String, Object>();
        content.put("newPassword", newPassword);
        content.put("link", generateResetPasswordLink(sessionID));

        RestTemplate restTemplate = new RestTemplate();
        JSONObject request = new JSONObject();
        request.put("to", to);
        request.put("type", "resetPassword");
        request.put("content", content);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), httpHeaders);

        String url = env.getProperty("mailservice.url");

        ResponseEntity<ResetPasswordMailResponse> resetPasswordEmailResponseEntity =
                restTemplate.exchange(url, HttpMethod.POST, entity, ResetPasswordMailResponse.class);

        ResetPasswordMailResponse resetPasswordMailResponse = resetPasswordEmailResponseEntity.getBody();

        return new AsyncResult<>(resetPasswordMailResponse);
    }

    private String generateResetPasswordLink(String sessionID) {
        return env.getProperty("host.url") + "/#/user/resetPassword/" + sessionID;
    }
}
