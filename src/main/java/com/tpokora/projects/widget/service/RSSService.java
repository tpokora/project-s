package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.AbstractWidgetModel;
import com.tpokora.projects.widget.model.rss.Feed;
import com.tpokora.projects.widget.rss.RSSParser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pokor on 16.04.2016.
 */
public class RSSService implements ContentService {

    @Autowired
    private RSSParser rssParser;

    @Override
    public AbstractWidgetModel getContent(String source) {

        Feed rssFeed = new Feed();
        rssFeed.setSource("test");
        return rssFeed;
    }

    private Feed convertRSSXMLtoWidgetModel(String source) {
        Feed feed = null;

        return feed;
    }
}
