package com.tpokora.projects.widget.service;

import com.tpokora.projects.config.AppConfig;
import com.tpokora.projects.config.DatabaseConfiguration;
import com.tpokora.projects.widget.dao.NewsDAO;
import com.tpokora.projects.widget.model.news.NewsWrapper;
import com.tpokora.projects.widget.utils.NewsTestUtils;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pokor on 10.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =  {
        AppConfig.class, DatabaseConfiguration.class
})
public class NewsServiceTest {

    @Autowired
    @Mock
    private NewsDAO newsDao;

    @Autowired
    @InjectMocks
    private ContentService newsService;

    private NewsWrapper newsWrapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        newsWrapper = new NewsWrapper();
        newsWrapper.setNewsList(NewsTestUtils.generateNewsList(3));
    }

    /**
     * Test getContent, should return news list
     */
    @Test
    public void getContent_returns_newsList() {
        Mockito.when(newsDao.getAllNews()).thenReturn(newsWrapper);
        NewsWrapper newsServiceContent = (NewsWrapper) newsService.getContent();
        Assert.assertTrue(!newsServiceContent.getNewsList().isEmpty());

        for (int i = 0; i < newsServiceContent.getNewsList().size(); i++) {
            Assert.assertTrue(newsServiceContent.getNewsList().get(i).getTitle().equalsIgnoreCase("news" + i));
            Assert.assertTrue(newsServiceContent.getNewsList().get(i).getLink().equalsIgnoreCase("http://news" + i + ".com"));
        }

    }
}
