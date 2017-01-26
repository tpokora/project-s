package com.tpokora.projects.user.dao;

import com.tpokora.projects.user.model.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pokor on 01.11.2016.
 */
@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, Integer> {
}
