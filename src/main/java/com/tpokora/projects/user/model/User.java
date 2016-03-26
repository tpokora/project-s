package com.tpokora.projects.user.model;

import com.tpokora.projects.articles.model.Article;
import com.tpokora.projects.common.model.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tomek on 2016-01-10.
 */
@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Article> articles;

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

    public User(Integer id, String username, String password, String email, String role, Set<Article> articles) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.articles = articles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
