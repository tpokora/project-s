package com.tpokora.projects.articles.service;

import com.tpokora.projects.articles.dao.ArticleRepository;
import com.tpokora.projects.articles.model.Article;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by pokor on 26.03.2016.
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleRepository articleRepo;

    @Override
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleRepo.findOne(id);
    }

    @Override
    public List<Article> getArticlesByTitle(String title) {
        return articleRepo.findByTitle(title);
    }

    @Override
    public Article createOrUpdateArticle(Article article) {
        if (article.getId() == null) {
            article.setCreateTime(new Date());
            Integer newArticleId = articleRepo.saveAndFlush(article).getId();
            return articleRepo.findOne(newArticleId);
        }

        Article updateArticle = articleRepo.findOne(article.getId());
        updateArticle.setTitle(article.getTitle());
        updateArticle.setContent(article.getContent());
        articleRepo.saveAndFlush(updateArticle);

        return articleRepo.findOne(article.getId());
    }

    @Override
    public void deleteArticleById(Integer id) {
        articleRepo.delete(id);
    }
}
