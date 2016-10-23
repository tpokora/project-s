package com.tpokora.projects.config;

import com.tpokora.projects.admin.service.TablesDetailsService;
import com.tpokora.projects.admin.service.TablesDetailsServiceImpl;
import com.tpokora.projects.articles.service.ArticleService;
import com.tpokora.projects.articles.service.ArticleServiceImpl;
import com.tpokora.projects.articles.web.rest.ArticleError;
import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.user.service.CustomUserDetailsService;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.service.UserServiceImpl;
import com.tpokora.projects.user.web.rest.UserError;
import com.tpokora.projects.widget.rss.RSSError;
import com.tpokora.projects.widget.rss.RSSParser;
import com.tpokora.projects.widget.rss.RSSParserImpl;
import com.tpokora.projects.widget.service.ContentService;
import com.tpokora.projects.widget.service.RSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by pokor on 28.02.2016.
 */
@Configuration
@PropertySource("classpath:application_${env:dev}.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    /*
		Application beans
	 */

    @Bean(name = "userError")
    public AbstractError getUserError() {
        return new UserError();
    }

    @Bean(name = "articleError")
    public AbstractError getArticleError() {
        return new ArticleError();
    }

    @Bean(name = "rssError")
    public AbstractError getRssError() {
        return new RSSError();
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

    @Bean(name = "articleService")
    public ArticleService getArticleService() {
        return new ArticleServiceImpl();
    }

    /*
        Widget Services
     */

    @Bean(name = "rssParser")
    public RSSParser getRssParser() {
        return new RSSParserImpl();
    }

    @Bean(name = "rssService")
    public ContentService getRssService() {
        return new RSSService();
    }

    // CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://" + env.getProperty("server.address") + ":" + env.getProperty("frontend.port"));
                // opening DEV application to request different then front end application - must be reconfigured in production system
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

}
