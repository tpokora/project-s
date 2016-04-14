package com.tpokora.projects.widget.rss;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by pokor on 14.04.2016.
 */
public class Feed {

    private String source;
    private Date lastUpdated;
    private ArrayList<FeedMessage> feeds;

    public Feed() {}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ArrayList<FeedMessage> getFeeds() {
        return feeds;
    }

    public void setFeeds(ArrayList<FeedMessage> feeds) {
        this.feeds = feeds;
    }
}
