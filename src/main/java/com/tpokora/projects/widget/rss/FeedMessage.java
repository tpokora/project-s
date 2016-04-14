package com.tpokora.projects.widget.rss;

/**
 * Created by pokor on 14.04.2016.
 */
public class FeedMessage {
    private String title;
    private String link;

    public FeedMessage() {}

    public FeedMessage(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
