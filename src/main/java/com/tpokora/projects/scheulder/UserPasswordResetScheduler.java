package com.tpokora.projects.scheulder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pokor on 26.10.2016.
 */
@Component
public class UserPasswordResetScheduler {

    private static final Logger logger = LoggerFactory.getLogger(UserPasswordResetScheduler.class);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private static final int USER_PASSWORD_RESET_SCHEDULER = 3600000;

    /**
     * Scheduler checks time of reset password creation and removes session after time
     */
    @Scheduled(fixedRate = USER_PASSWORD_RESET_SCHEDULER)
    public void userResetPasswordScheduler() {
        logger.info("Current time is {}", SIMPLE_DATE_FORMAT.format(new Date()));
        // Check DB if sessions of reset password are overdue
    }
}
