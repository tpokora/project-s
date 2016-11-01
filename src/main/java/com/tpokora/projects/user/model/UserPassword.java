package com.tpokora.projects.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tpokora.projects.common.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by pokor on 01.11.2016.
 */
@Entity
@Table(name = "USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserPassword extends AbstractEntity {

    @Column(name = "PASSWORD")
    private String password;

    public UserPassword() {

    }

    public UserPassword(String password) {
        this.password = password;
    }

    public UserPassword(Integer id, String password) {
        super(id);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
