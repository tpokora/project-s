package com.tpokora.projects.common.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by pokor on 28.02.2016.
 */
@RestController
public class AuthController {

    @RequestMapping(value = "/auth")
    public Principal user(Principal user) {
        return user;
    }

}
