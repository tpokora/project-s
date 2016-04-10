package com.tpokora.projects.widget.service.news;

import com.tpokora.projects.widget.dao.news.NewsDAO;
import com.tpokora.projects.widget.model.news.NewsWrapper;
import com.tpokora.projects.widget.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pokor on 07.04.2016.
 */
public class NewsService implements ContentService {

    @Autowired
    private NewsDAO newsDAO;

    public NewsService() {
    }

    public NewsWrapper getContent() {
        return newsDAO.getAllNews();
    }
}
