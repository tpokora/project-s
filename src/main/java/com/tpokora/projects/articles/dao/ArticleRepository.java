package com.tpokora.projects.articles.dao;

import com.tpokora.projects.articles.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pokor on 26.03.2016.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    public Article findByTitle(String title);
}
