package com.tpokora.projects.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pokor on 01.03.2016.
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.tpokora.projects.user.model.User user = userService.getUserByUsername(s);

        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(com.tpokora.projects.user.model.User user,
                                            List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String role) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority(role));

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

        return result;
    }
}
