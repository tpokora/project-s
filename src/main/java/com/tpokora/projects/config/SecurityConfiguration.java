package com.tpokora.projects.config;

import com.tpokora.projects.config.filter.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@PropertySource("classpath:properties/${env:dev}.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    private static final String USERS_NAMES_QUERY = "select username, password from users where username = ?";
    private static final String USERS_ROLES_QUERY = "select username, role from users where username = ?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/app/views/home.html", "/app/views/login.html", "/app/views/users/user_new.html", "/app/views/article/**", "/app/views/widget/**").permitAll()
                .antMatchers("/app/views/admin/**").hasRole("ADMIN")
                .antMatchers("/app/views/users/**", "/app/views/article/article_new.html").hasAnyRole("ADMIN, USER")
                // TODO: Access Denied Page does not work
                .and().formLogin().loginPage("/app/views/login.html")
                .usernameParameter("username").passwordParameter("password")
                .and().logout().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/app/views/login.html?error");

        if (env.getProperty("status").equals("dev")) {
            http.csrf().disable();
        } else {
            http.csrf().csrfTokenRepository(csrfTokenRepository());
            http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
        }
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
