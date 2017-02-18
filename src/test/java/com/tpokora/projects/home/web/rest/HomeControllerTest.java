package com.tpokora.projects.home.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tpokora.projects.articles.model.Article;
import com.tpokora.projects.articles.service.ArticleService;
import com.tpokora.projects.articles.utils.ArticleTestUtils;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import com.tpokora.projects.user.model.User;
import com.tpokora.projects.user.utils.UserTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

/**
 * Created by pokor on 05.03.2016.
 */
public class HomeControllerTest extends AbstractControllerTest {

    private final static String TITLE = "Title";
    private final static String HEADER = "Header";

    @Mock
    ArticleService articleService;

    @Mock
    Environment env;

    @InjectMocks
    HomeController homeController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void getHome_ok() throws Exception {
        User testUser = UserTestUtils.generateUsers(1).get(0);
        Article testArticle = ArticleTestUtils.generateArticles(1, testUser.getId()).get(0);
        when(env.getProperty("home.page.title")).thenReturn(TITLE);
        when(env.getProperty("home.page.header")).thenReturn(HEADER);
        when(articleService.getNewestArticle()).thenReturn(testArticle);
        mockMvc.perform(get("/rest/home"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(jsonPath("$.title").isString())
                .andExpect(jsonPath("$.header").isString())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content.id").isNumber())
                .andExpect(jsonPath("$.content.title").isString())
                .andExpect(jsonPath("$.content.content").isString())
                .andExpect(jsonPath("$.content.createTime").exists())
                .andExpect(jsonPath("$.content.user").exists())
                .andExpect(jsonPath("$.content.user.id").exists());
                //.andExpect(jsonPath("$.content.user.username").exists());
                //.andExpect(jsonPath("$.content.user.email").exists());
                //.andExpect(jsonPath("$.content.user.role").exists());
    }
}
