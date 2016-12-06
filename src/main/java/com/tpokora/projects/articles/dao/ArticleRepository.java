package com.tpokora.projects.articles.dao;

import com.tpokora.projects.articles.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by pokor on 26.03.2016.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    public List<Article> findByTitle(String title);
    public List<Article> findFirstByOrderByCreateTimeAsc();
}
