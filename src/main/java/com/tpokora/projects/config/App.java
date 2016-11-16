package com.tpokora.projects.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by pokor on 28.02.2016.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.tpokora.projects" })
@EnableAutoConfiguration
@EnableScheduling
public class App {

    public static void main(String[] args) {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "7080";
        }
        
        System.setProperty("server.port", webPort);
        SpringApplication.run(App.class, args);
    }
}
