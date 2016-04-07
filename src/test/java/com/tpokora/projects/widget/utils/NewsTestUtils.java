package com.tpokora.projects.widget.utils;

import com.tpokora.projects.widget.model.news.News;

import java.util.ArrayList;

/**
 * Created by pokor on 07.04.2016.
 */
public class NewsTestUtils {

    public static ArrayList<News> generateNewsList(int amount) {
        ArrayList<News> newsList = new ArrayList<News>();
        for (int i = 0; i < amount; i++) {
            newsList.add(generateNews(i));
        }

        return newsList;
    }

    public static News generateNews(int i) {
        return new News("News" + i, "http://news" + i + ".com");
    }
}
