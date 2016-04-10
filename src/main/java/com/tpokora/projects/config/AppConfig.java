package com.tpokora.projects.config;

import com.tpokora.projects.admin.service.TablesDetailsService;
import com.tpokora.projects.admin.service.TablesDetailsServiceImpl;
import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.user.service.CustomUserDetailsService;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.service.UserServiceImpl;
import com.tpokora.projects.user.web.rest.UserError;
import com.tpokora.projects.widget.service.ContentService;
import com.tpokora.projects.widget.service.WidgetService;
import com.tpokora.projects.widget.service.WidgetServiceImpl;
import com.tpokora.projects.widget.service.news.NewsService;
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

    @Bean(name = "tablesDetailsService")
    public TablesDetailsService getTablesDetailsService() {
        return new TablesDetailsServiceImpl();
    }

    /*
        Widget Services
     */

    @Bean(name = "widgetService")
    public WidgetService widgetService() {
        return new WidgetServiceImpl();
    }

    @Bean(name = "newsService")
    public ContentService getNewsService() {
        return new NewsService();
    }
}
