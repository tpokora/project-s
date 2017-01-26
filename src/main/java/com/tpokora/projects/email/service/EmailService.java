package com.tpokora.projects.email.service;

import com.tpokora.projects.email.model.EmailResponse;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.concurrent.Future;

/**
 * Created by pokor on 26.12.2016.
 */
@Service("emailService")
@PropertySource("classpath:properties/${env:app}.properties")
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private Environment env;

    public Future<EmailResponse> sendUserRegistrationCompleteEmail(String login, String email) {
        HashMap<String, Object> content = new HashMap<>();
        content.put("login", login);

        EmailResponse emailResponse = sendEmail(email, "registration", content).getBody();
        logger.info("Sending email to: {}", email);

        return new AsyncResult<>(emailResponse);
    }

    public Future<EmailResponse> sendResetPasswordEmail(String to, String newPassword, String sessionID) {
        HashMap<String, Object> content = new HashMap<String, Object>();
        content.put("newPassword", newPassword);
        content.put("link", generateResetPasswordLink(sessionID));

        EmailResponse emailResponse = sendEmail(to, "resetPassword", content).getBody();

        return new AsyncResult<>(emailResponse);
    }

    private ResponseEntity<EmailResponse> sendEmail(String to, String type, HashMap<String, Object> content) {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject request = new JSONObject();
        request.put("to", to);
        request.put("type", type);
        request.put("content", content);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), httpHeaders);

        String url = env.getProperty("mailservice.url");

        return restTemplate.exchange(url, HttpMethod.POST, entity, EmailResponse.class);
    }

    private String generateResetPasswordLink(String sessionID) {
        return env.getProperty("host.url") + "/#/user/resetPassword/" + sessionID;
    }
}
