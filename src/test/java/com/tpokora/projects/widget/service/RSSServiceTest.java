package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.AbstractWidgetModel;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pokor on 16.04.2016.
 */
public class RSSServiceTest {

    @Autowired
    private ContentService rssService;

    @Before
    public void setup() {}

    @Test
    public void getContent_test_elementExists() {
        AbstractWidgetModel model = rssService.getContent("test");

        Assert.assertNotNull(model);
    }
}
