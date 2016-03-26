package com.tpokora.projects.articles.service;

import com.tpokora.projects.articles.model.Article;

import java.util.List;

/**
 * Created by pokor on 26.03.2016.
 */
public interface ArticleService {
    public List<Article> getAllArticles();
    public Article getArticleById(Integer id);
    public Article getArticleByTitle(String title);
    public Article createOrUpdateArticle(Article article);
    public void deleteArticleById(Integer id);
}
