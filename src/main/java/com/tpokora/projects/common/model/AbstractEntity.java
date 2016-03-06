package com.tpokora.projects.common.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by pokor on 11.02.2016.
 */
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    protected Long id;

    public AbstractEntity() {

    }

    public AbstractEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
