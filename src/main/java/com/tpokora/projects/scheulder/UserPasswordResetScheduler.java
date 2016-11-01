package com.tpokora.projects.scheulder;

import com.tpokora.projects.user.model.UserResetPassword;
import com.tpokora.projects.user.service.UserResetPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pokor on 26.10.2016.
 */
@Component
public class UserPasswordResetScheduler {

    private static final Logger logger = LoggerFactory.getLogger(UserPasswordResetScheduler.class);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private static final int USER_PASSWORD_RESET_SCHEDULER = 3600000;

    @Autowired
    private UserResetPasswordService userResetPasswordService;

    /**
     * Scheduler checks time of reset password creation and removes session after time
     */
    @Scheduled(fixedRate = USER_PASSWORD_RESET_SCHEDULER)
    public void userResetPasswordScheduler() {
        List<UserResetPassword> userResetPasswordList = userResetPasswordService.getAllUserResetPasswordSessions();
        for (UserResetPassword userResetPassword : userResetPasswordList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR_OF_DAY, -2);
            Date date = calendar.getTime();
            if (userResetPassword.getCreateTime().before(date)) {
                logger.info("Record: " + userResetPassword.getId() + " - CreateTime: " + userResetPassword.getCreateTime().toString());
                logger.info("Removing sessionID: " + userResetPassword.getSessionId());
                userResetPasswordService.removeUserResetPasswordBySessionID(userResetPassword.getSessionId());
            }
        }
    }
}
