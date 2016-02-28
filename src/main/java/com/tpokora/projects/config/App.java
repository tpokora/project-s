package com.tpokora.projects.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by pokor on 28.02.2016.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.tpokora.projects" })
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}