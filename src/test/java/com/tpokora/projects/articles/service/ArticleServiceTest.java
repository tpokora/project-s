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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pokor on 26.03.2016.
 */
public class ArticleServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    private Article testArticle;
    private User articleUser;

    @Autowired
    private ArticleService articleService;

    /**
     * Sets up user for testing
     */
    @Before
    public void setup() {
        articleUser = new User();
        articleUser.setUsername("ArticleUser");
        articleUser.setPassword("test");
        articleUser.setEmail("articleuser@test.com");

        testArticle = new Article();
        testArticle.setTitle("TestArticle");
        testArticle.setContent("TestArticleContent");
        testArticle.setCreateTime(new Date());
    }

    /**
     * Test getting article by title
     */
    @Test
    @Transactional
    @Rollback(true)
    public void getArticlesByTitle_testArticle_success() {
        userService.createOrUpdateUser(articleUser);
        articleUser.setId(userService.getUserByUsername(articleUser.getUsername()).getId());
        testArticle.setUser(articleUser);

        articleService.createOrUpdateArticle(testArticle);

        List<Article> articles = articleService.getArticlesByTitle(testArticle.getTitle());

        for (Article article : articles) {
            Assert.assertEquals(true, article.getTitle().equals(testArticle.getTitle()));
        }
    }

    /**
     * Tests if adding and updating article finish with success
     * @throws SQLException
     */
    @Test
    @Transactional
    @Rollback(true)
    public void createOrUpdateArticle_newArticle_success() throws SQLException {
        userService.createOrUpdateUser(articleUser);
        articleUser.setId(userService.getUserByUsername(articleUser.getUsername()).getId());
        testArticle.setUser(articleUser);

        articleService.createOrUpdateArticle(testArticle);

        testArticle = articleService.getArticlesByTitle(testArticle.getTitle()).get(0);
        Assert.assertEquals(testArticle.getTitle(), testArticle.getTitle());

        testArticle.setTitle(testArticle.getTitle() + "Updated");

        articleService.createOrUpdateArticle(testArticle);

        Assert.assertEquals(testArticle.getTitle(), articleService.getArticlesByTitle(testArticle.getTitle()).get(0).getTitle());
    }

    /**
     *  Test deleting article
     */
    @Test
    @Transactional
    @Rollback(true)
    public void deleteArticleById_Id_success() {
        userService.createOrUpdateUser(articleUser);
        articleUser.setId(userService.getUserByUsername(articleUser.getUsername()).getId());
        testArticle.setUser(articleUser);

        articleService.createOrUpdateArticle(testArticle);

        testArticle = articleService.getArticlesByTitle(testArticle.getTitle()).get(0);

        articleService.deleteArticleById(testArticle.getId());

        Assert.assertNull(articleService.getArticleById(testArticle.getId()));
    }
}
