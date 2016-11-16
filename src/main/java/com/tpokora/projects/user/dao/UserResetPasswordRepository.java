package com.tpokora.projects.user.dao;

import com.tpokora.projects.user.model.UserResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pokor on 27.10.2016.
 */
@Repository
public interface UserResetPasswordRepository extends JpaRepository<UserResetPassword, Integer> {
    public UserResetPassword findBySessionId(String sessionID);
}
