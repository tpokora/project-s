package com.tpokora.projects.user.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorTypes;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.model.nullobjects.NullUser;
import com.tpokora.projects.user.service.UserService;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 2016-01-19.
 */
@RestController
@RequestMapping("/rest/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private static RESTResponseWrapper restResponse = new RESTResponseWrapper();

    private static final String USER_RESPONSE_STRING = "users";

    @Autowired
    UserService userService;

    @Autowired
    AbstractError userError;

    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> getAllUsers() {
        restResponse.clearResponse();
        logger.info("Looking for users...");
        if (userService.getAllUsers().isEmpty()) {
            logger.error("No USERS returned to: " + this.getClass().getSimpleName());
            userError.setError(ErrorTypes.USER_NOT_EXISTS);
            restResponse.addError(userError);
            return new ResponseEntity<RESTResponseWrapper>(HttpStatus.NOT_FOUND);
        }

        logger.info("Add users list to response content...");
        restResponse.addContent(USER_RESPONSE_STRING, userService.getAllUsers());

        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> getUserById(@PathVariable("id") int id) {
        restResponse.clearResponse();
        logger.info("Looking for user with id: " + id + " ...");
        if (userService.getUserById(id) instanceof NullUser) {
            logger.error("No USERS with id: " + id + " returned to: " + this.getClass().getSimpleName());
            userError.setError(ErrorTypes.USER_NOT_EXISTS);
            restResponse.addError(userError);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        restResponse.addContent(USER_RESPONSE_STRING, userToArray(userService.getUserById(id)));
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> getUserByUsername(@PathVariable("username") String username) {
        restResponse.clearResponse();
        logger.info("Looking for user with username: " + username + " ...");
        if (userService.getUserByUsername(username) instanceof NullUser) {
            logger.error("No USERS with username: " + username + " returned to: " + this.getClass().getSimpleName());
            userError.setError(ErrorTypes.USER_NOT_EXISTS);
            restResponse.addError(userError);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        restResponse.addContent(USER_RESPONSE_STRING, userToArray(userService.getUserByUsername(username)));
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> createNewUser(@RequestBody User user, UriComponentsBuilder ucb) throws Exception {
        restResponse.clearResponse();
        logger.info("Creating new user");
        User newUser = user;
        newUser.setRole("ROLE_USER");

        try {
            userService.createOrUpdateUser(newUser);
        } catch(ConstraintViolationException e) {
            userError.setError(ErrorTypes.USER_ALREADY_EXISTS);
            restResponse.addError(userError);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        restResponse.addContent(USER_RESPONSE_STRING, userToArray(newUser));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucb.path("/home").build().toUri());
        return new ResponseEntity<RESTResponseWrapper>(restResponse, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        restResponse.clearResponse();
        System.out.println("Updating user: " + id);

        User currentUser = userService.getUserById(id);

        if (currentUser instanceof NullUser) {
            userError.setError(ErrorTypes.USER_NOT_EXISTS);
            restResponse.addError(userError);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setEmail(user.getEmail());

        userService.createOrUpdateUser(currentUser);
        restResponse.addContent(USER_RESPONSE_STRING, userToArray(currentUser));
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> removeUserById(@PathVariable("id") int id, UriComponentsBuilder ucb) {
        restResponse.clearResponse();
        logger.info("Removing user with id: " + id + " ...");
        User deleteUser = userService.getUserById(id);
        if (deleteUser instanceof NullUser) {
            logger.error("Unable to delete user. User not exists");
            userError.setError(ErrorTypes.USER_NOT_EXISTS);
            restResponse.addError(userError);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        userService.removeUserById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucb.path("/home").build().toUri());
        return new ResponseEntity<RESTResponseWrapper>(headers, HttpStatus.NO_CONTENT);
    }

    private List<User> userToArray(User user) {
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        return userList;
    }
}
