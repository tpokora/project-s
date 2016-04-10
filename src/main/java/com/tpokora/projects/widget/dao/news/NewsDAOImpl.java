package com.tpokora.projects.widget.dao.news;

import com.tpokora.projects.widget.model.news.News;
import com.tpokora.projects.widget.model.news.NewsWrapper;

/**
 * Created by pokor on 10.04.2016.
 */
public class NewsDAOImpl implements NewsDAO {

    private NewsWrapper newsWrapper;

    @Override
    public NewsWrapper getAllNews() {
        News news1 = new News("news1", "http://news1.com");
        News news2 = new News("news2", "http://news1.com");
        News news3 = new News("news3", "http://news1.com");

        newsWrapper = new NewsWrapper();
        newsWrapper.addNews(news1);
        newsWrapper.addNews(news2);
        newsWrapper.addNews(news3);

        return newsWrapper;
    }
}
