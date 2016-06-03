package com.tpokora.projects.user.model;

import com.fasterxml.jackson.annotation.*;
import com.tpokora.projects.articles.model.Article;
import com.tpokora.projects.common.model.AbstractEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Tomek on 2016-01-10.
 */
@Entity
@Table(name = "USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AbstractEntity {

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Article> articles;

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

    public User(Integer id, String username, String password, String email, String role, List<Article> articles) {
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
