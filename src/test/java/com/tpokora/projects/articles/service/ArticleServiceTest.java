package com.tpokora.projects.articles.service;

import com.tpokora.projects.articles.model.Article;
import com.tpokora.projects.common.service.AbstractServiceTest;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.service.UserService;
import com.tpokora.projects.user.utils.UserTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by pokor on 26.03.2016.
 */
public class ArticleServiceTest extends AbstractServiceTest {

    @Autowired
    UserService userService;

    User user;

    @Autowired
    ArticleService articleService;

    /**
     * Sets up user for testing
     */
    @Before
    @Transactional
    @Rollback(true)
    public void setup() {
        user = new User();
        user.setUsername("ArticleUser");
        user.setPassword("test");
        user.setEmail("articleuser@test.com");

        userService.createOrUpdateUser(user);

        user = userService.getUserByUsername(user.getUsername());
    }

    /**
     * Test getting all articles
     */
    @Test
    @Transactional
    public void getAllArticles_success() {
        List<Article> articles = articleService.getAllArticles();

        Assert.assertEquals(false, articles.isEmpty());
    }

    /**
     * Test getting article by id
     */
    @Test
    @Transactional
    public void getArticleById_1_success() {
        Article article = articleService.getArticleById(1);

        Assert.assertEquals(true, article.getId() == 1);
    }

    /**
     * Test getting article by title
     */
    @Test
    @Transactional
    public void getArticlesByTitle_testArticle_success() {
        String title = "testArticle";
        List<Article> articles = articleService.getArticlesByTitle(title);

        articles.forEach(article -> {
            Assert.assertEquals(true, article.getTitle().equals(title));
        });
    }

    /**
     * Tests if adding and updating article finish with success
     * @throws SQLException
     */
    @Test
    @Transactional
    @Rollback(true)
    public void createOrUpdateArticle_newArticle_success() throws SQLException {
        String title = "Test Article";
        String updatedTitle = "Test Article - updated";
        String content = "Article content for testing purposes";
        Date date = new Date();

        Article newArticle = new Article();
        newArticle.setTitle(title);
        newArticle.setContent(content);
        newArticle.setCreateTime(date);
        newArticle.setUser(user);

        Article addedArticle = articleService.createOrUpdateArticle(newArticle);

        Assert.assertEquals(title, addedArticle.getTitle());

        newArticle.setTitle(updatedTitle);

        Article updatedArticle = articleService.createOrUpdateArticle(newArticle);

        Assert.assertEquals(updatedTitle, updatedArticle.getTitle());
    }

    /**
     *  Test deleting user
     */
    @Test
    @Transactional
    @Rollback(true)
    public void deleteArticleById_1_success() {
        Assert.assertNotNull(articleService.getArticleById(1));

        int id = 1;
        articleService.deleteArticleById(id);

        Assert.assertNull(articleService.getArticleById(id));
    }
}
