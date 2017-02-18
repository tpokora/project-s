package com.tpokora.projects.widget.service;

import com.tpokora.projects.common.AbstractTest;
import com.tpokora.projects.widget.model.rss.Feed;
import com.tpokora.projects.widget.model.rss.FeedMessage;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by pokor on 16.04.2016.
 */
@Ignore
public class RSSServiceTest extends AbstractTest {

    private final static Logger logger = LoggerFactory.getLogger(RSSServiceTest.class);

    @Autowired
    private ContentService rssService;

    @Before
    public void setup() {}

    /**
     * This test test if XML Feed msg into Feed object
     */
    @Test
    public void getContent_test_elementExists() {
        Feed model = (Feed) rssService.getContent("test");

        Assert.assertNotNull(model);
        Assert.assertTrue("Expected 'RSS', got: " + model.getSource(), model.getName().equals("RSS"));
        Assert.assertTrue("Expected 'http://www.vogella.com', got: " + model.getSource(), model.getSource().equals("http://www.vogella.com"));

        for (FeedMessage feedMsg : model.getFeeds()) {
            Assert.assertTrue(!feedMsg.getTitle().equals("") && feedMsg.getTitle() != null);
            Assert.assertTrue(feedMsg.getLink().contains("http://") && feedMsg.getLink() != null);
        }
    }


    /**
     * Test if getRSSSources retrieves list of elements
     */
    @Test
    public void getRSSSources_elementsList() {
        ArrayList<String> elementsList = (ArrayList<String>) rssService.getRSSSources();

        Assert.assertNotNull(elementsList);
        Assert.assertTrue(!elementsList.isEmpty());
    }
}
