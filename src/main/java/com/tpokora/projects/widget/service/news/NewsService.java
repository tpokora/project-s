package com.tpokora.projects.widget.service.news;

import com.tpokora.projects.widget.dao.NewsDAO;
import com.tpokora.projects.widget.model.WidgetAbstractModel;
import com.tpokora.projects.widget.model.news.NewsWrapper;
import com.tpokora.projects.widget.service.ContentService;

/**
 * Created by pokor on 07.04.2016.
 */
public class NewsService implements ContentService {

    private NewsWrapper newsWrapper;

    private NewsDAO newsDAO;

    public NewsService() {
        newsWrapper = new NewsWrapper();
    }


    public WidgetAbstractModel getContent() {

        return newsWrapper;
    }
}
