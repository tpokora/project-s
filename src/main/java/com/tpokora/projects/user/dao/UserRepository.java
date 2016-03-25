package com.tpokora.projects.user.dao;

import com.tpokora.projects.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomek on 2016-01-16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
