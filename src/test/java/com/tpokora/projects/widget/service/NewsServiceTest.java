package com.tpokora.projects.widget.service;


import com.tpokora.projects.widget.model.news.News;
import com.tpokora.projects.widget.model.news.NewsWrapper;
import com.tpokora.projects.widget.utils.NewsTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by pokor on 07.04.2016.
 */
public class NewsServiceTest {

    NewsService newsService;

    @Before
    public void setup() {
        newsService = new NewsService();
    }

    @Test
    public void getNewsFrom_test_newsList() {
        NewsWrapper newsWrapper = new NewsWrapper();
        List<News> newsList = NewsTestUtils.generateNewsList(3);

        for (News news: newsList) {
            newsWrapper.addNews(news);
        }

        newsService.setNewsWrapper(newsWrapper);

        Assert.assertTrue(!newsService.getNewsWrapper().getNewsList().isEmpty());

        for (int i = 0; i < newsList.size(); i++) {
            Assert.assertEquals("News" + i, newsService.getNewsWrapper().getNewsList().get(i).getTitle());
            Assert.assertEquals("http://news" + i + ".com", newsService.getNewsWrapper().getNewsList().get(i).getLink());
        }
    }
}
