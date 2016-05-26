package com.tpokora.projects.articles.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tpokora.projects.common.model.AbstractEntity;
import com.tpokora.projects.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pokor on 25.03.2016.
 */
@Entity
@Table(name = "ARTICLE")
public class Article extends AbstractEntity implements Serializable {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATE_TIME")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date createTime;


    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    /**
     * @JsonManagedReference commented out due to error in handling reference when POSTed
     */
    private User user;

    public Article() {

    }

    public Article(Integer id, String title, String content, Date createTime) {
        super(id);
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public Article(Integer id, String title, String content, Date createTime, User user) {
        super(id);
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
