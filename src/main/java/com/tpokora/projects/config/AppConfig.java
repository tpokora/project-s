package com.tpokora.projects.config;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorWrapper;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import com.tpokora.projects.user.dao.UserDAO;
import com.tpokora.projects.user.dao.UserDAOImpl;
import com.tpokora.projects.user.service.CustomUserDetailsService;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.service.UserServiceImpl;
import com.tpokora.projects.user.web.rest.UserError;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by pokor on 28.02.2016.
 */
@Configuration
public class AppConfig {

    /*
		Application beans
	 */

    @Autowired
    @Bean(name = "userError")
    public AbstractError getUserError() {
        return new UserError();
    }

	/*
		DAO Objects bean configuration
	*/

    @Autowired
    @Bean(name = "userDao")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
        return new UserDAOImpl(sessionFactory);
    }

	/*
		Service classes
	 */

    @Bean(name = "userService")
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean(name = "userDetailsService")
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailsService();
    }
}
