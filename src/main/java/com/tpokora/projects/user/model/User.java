package com.tpokora.projects.user.model;

import com.tpokora.projects.common.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Tomek on 2016-01-10.
 */
@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {
    private String username;
    private String password;
    private String email;
    private String role;

    public User() {
        super();
    }

    public User(Integer id, String username, String password, String email, String role) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
