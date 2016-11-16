package com.tpokora.projects.articles.web;

import com.tpokora.projects.articles.service.ArticleService;
import com.tpokora.projects.articles.utils.ArticleTestUtils;
import com.tpokora.projects.articles.web.rest.ArticleController;
import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.web.rest.AbstractControllerTest;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

/**
 * Created by pokor on 03.06.2016.
 */
public class ArticleControllerTest extends AbstractControllerTest{

    private static final Logger logger = Logger.getLogger(ArticleControllerTest.class);

    @Mock
    AbstractError articleError;

    @Mock
    ArticleService articleService;

    @InjectMocks
    ArticleController articleController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    public void getArticles_articlesExists() throws Exception {
        when(articleService.getAllArticles()).thenReturn(ArticleTestUtils.generateArticles(3, 1));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/article/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.articles").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.articles[0].title").isString());
    }
}
