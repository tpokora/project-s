package com.tpokora.projects.articles.model;

import com.tpokora.projects.common.model.AbstractEntity;
import com.tpokora.projects.user.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by pokor on 25.03.2016.
 */
@Entity
@Table(name = "ARTICLE")
public class Article extends AbstractEntity {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    @Lob
    private Blob content;

    @Column(name = "CREATE_TIME")
    @DateTimeFormat
    private Date createTime;


    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    private User user;

    public Article() {

    }

    public Article(Integer id, String title, Blob content, Date createTime) {
        super(id);
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public Article(Integer id, String title, Blob content, Date createTime, User user) {
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

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
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
