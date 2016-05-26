package com.tpokora.projects.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by pokor on 26.05.2016.
 */
@Configuration
@PropertySource("classpath:properties/prod.properties")
public class DatabaseTestConfiguration extends DatabaseConfiguration {
}
