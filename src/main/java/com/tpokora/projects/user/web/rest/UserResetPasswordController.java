package com.tpokora.projects.user.web.rest;

import com.tpokora.projects.common.web.RESTResponseWrapper;
import com.tpokora.projects.user.service.UserResetPasswordService;
import com.tpokora.projects.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pokor on 29.10.2016.
 */
@RestController
@RequestMapping(value = "/rest")
public class UserResetPasswordController {

    private static final Logger logger = LoggerFactory.getLogger(UserResetPasswordController.class);

    private static RESTResponseWrapper restResponse = new RESTResponseWrapper();

    @Autowired
    private UserService userService;

    @Autowired
    private UserResetPasswordService userResetPasswordService;

    @RequestMapping(value = "/user/reset/{sessionID}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> resetUserPassword(@PathVariable("sessionID") String sessionID) {
        restResponse.clearResponse();

        if (userResetPasswordService.findBySessionId(sessionID) == null) {
            logger.error("Session with sessionID: " + sessionID + " not found");
            // add error
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }
}
