package com.tpokora.projects.user.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorTypes;
import com.tpokora.projects.common.utils.SecurityUtilities;
import com.tpokora.projects.common.utils.SessionIdentifierGenerator;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import com.tpokora.projects.email.service.EmailService;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.UserPassword;
import com.tpokora.projects.user.model.UserResetPassword;
import com.tpokora.projects.user.model.nullobjects.NullUser;
import com.tpokora.projects.user.service.UserResetPasswordService;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.service.UserPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by pokor on 29.10.2016.
 */
@RestController
@RequestMapping(value = "/rest")
@EnableAutoConfiguration
public class UserResetPasswordController {

    private static final Logger logger = LoggerFactory.getLogger(UserResetPasswordController.class);

    private static RESTResponseWrapper restResponse = new RESTResponseWrapper();

    @Autowired
    private UserService userService;

    @Autowired
    private UserResetPasswordService userResetPasswordService;

    @Autowired
    private UserPasswordService userPasswordService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AbstractError userError;

    /**
     * Creates new session with new and old hashed password in DB
     * @param user
     * @return returns sessionID of password reset
     */
    @RequestMapping(value = "/user/reset/new", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> generateSessionID(@RequestBody User user) {

        if (userService.getUserById(user.getId()) == null ) {
            addUserErrorToResponse(ErrorTypes.USER_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        String sessionID = SessionIdentifierGenerator.generateSessionID();
        String tempPassword = SecurityUtilities.randomString(SecurityUtilities.CHARSET_AZ_09, SecurityUtilities.PASSWORD_LENGTH);
        String hashedTempPassword = SecurityUtilities.hashingPassword(tempPassword);
        String oldPassword = userPasswordService.getUserById(user.getId()).getPassword();

        UserResetPassword userResetPassword = new UserResetPassword(sessionID, hashedTempPassword, oldPassword, new Date(), user);
        emailService.sendResetPasswordEmail(user.getEmail(), tempPassword, userResetPassword.getSessionId());

        UserResetPassword updatedUser = userResetPasswordService.createOrUpdateUserResetPassword(userResetPassword);
        restResponse.addContent("userResetSession", updatedUser);
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/reset/{sessionID}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> findUserBySessionId(@PathVariable("sessionID") String sessionID) {
        restResponse.clearResponse();

        if (userResetPasswordService.findBySessionId(sessionID) == null) {
            logger.error("Session with sessionID: " + sessionID + " not found");
            addUserErrorToResponse(ErrorTypes.USER_RESET_PASSWORD_SESSIONID_NOT_FOUND);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        UserResetPassword userResetPassword = userResetPasswordService.findBySessionId(sessionID);

        if  (userService.getUserById(userResetPassword.getUser().getId()) == null) {
            addUserErrorToResponse(ErrorTypes.USER_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        User user = userService.getUserById(userResetPassword.getUser().getId());
        restResponse.addContent("user", user);
        restResponse.addContent("sessionID", userResetPassword.getSessionId());
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/reset/{sessionID}/changePassword", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> changeUserPassword(@PathVariable(value = "sessionID") String sessionID) {
        restResponse.clearResponse();
        UserResetPassword userResetPassword = userResetPasswordService.findBySessionId(sessionID);
        if (userResetPassword == null) {
            logger.error("Session with sessionID: " + sessionID + " not found");
            addUserErrorToResponse(ErrorTypes.USER_RESET_PASSWORD_SESSIONID_NOT_FOUND);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        if (userService.getUserById(userResetPassword.getUser().getId()) instanceof NullUser) {
            addUserErrorToResponse(ErrorTypes.USER_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        User userToUpdate = userService.getUserById(userResetPassword.getUser().getId());
        changeUserPasswordToTemp(userToUpdate, userResetPassword.getTempPassword());
        User updatedUser = userService.getUserById(userToUpdate.getId());
        // TODO: element is not being removed from from DB
        userResetPasswordService.removeUserResetPasswordBySessionID(userResetPassword.getSessionId());
        restResponse.addContent("user", userToUpdate);
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    private void changeUserPasswordToTemp(User user, String tempPassword) {
        UserPassword oldPassword = userPasswordService.getUserById(user.getId());
        UserPassword newPassword = new UserPassword(user.getId(), tempPassword);
        userPasswordService.updateUserPassword(newPassword);
    }

    private void addUserErrorToResponse(ErrorTypes error) {
        userError.setError(error);
        restResponse.addError(userError);
    }
}
