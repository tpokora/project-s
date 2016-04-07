package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.news.NewsWrapper;

/**
 * Created by pokor on 07.04.2016.
 */
public class NewsService {

    private NewsWrapper newsWrapper;

    public NewsService() {}

    public NewsWrapper getNewsWrapper() {
        return newsWrapper;
    }

    public void setNewsWrapper(NewsWrapper newsWrapper) {
        this.newsWrapper = newsWrapper;
    }


}
