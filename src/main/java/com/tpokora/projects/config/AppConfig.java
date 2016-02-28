package com.tpokora.projects.config;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorWrapper;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import com.tpokora.projects.user.dao.UserDAO;
import com.tpokora.projects.user.dao.UserDAOImpl;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.service.UserServiceImpl;
import com.tpokora.projects.user.web.rest.UserError;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pokor on 28.02.2016.
 */
@Configuration
public class AppConfig {

    /*
		Application beans
	 */

    // Response Bean
    @Autowired
    @Bean(name = "restResponse")
    public RESTResponseWrapper getResponseWrapper() {
        return new RESTResponseWrapper();
    }

    // Errors Bean
    @Autowired
    @Bean(name = "errors")
    public ErrorWrapper getErrors() {
        return new ErrorWrapper();
    }

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
}