package com.tpokora.projects.widget.model.news;

import com.tpokora.projects.widget.model.AbstractWidgetModel;

import java.util.ArrayList;

/**
 * Created by pokor on 07.04.2016.
 */
public class NewsWrapper extends AbstractWidgetModel {

    private ArrayList<News> newsList;

    public NewsWrapper() {
        super();
        newsList = new ArrayList<News>();
    }

    public NewsWrapper(String title) {
        super(title);
        newsList = new ArrayList<News>();
    }

    public void addNews(News news) {
        newsList.add(news);
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void clearNewsList() {
        newsList.clear();
    }


}
