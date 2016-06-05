package com.tpokora.projects.articles.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpokora.projects.common.model.AbstractEntity;
import com.tpokora.projects.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pokor on 05.06.2016.
 */
@Entity
@Table(name = "ARTICLE")
public class ListArticle extends AbstractEntity implements Serializable {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CREATE_TIME")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date createTime;


    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    /**
     * @JsonManagedReference commented out due to error in handling reference when POSTed
     */
    private User user;

    public ListArticle() {

    }

    public ListArticle(String title, Date createTime, User user) {
        this.title = title;
        this.createTime = createTime;
        this.user = user;
    }

    public ListArticle(Integer id, String title, Date createTime, User user) {
        super(id);
        this.title = title;
        this.createTime = createTime;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
