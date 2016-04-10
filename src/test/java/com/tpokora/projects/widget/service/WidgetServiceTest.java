package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.NullWidgetObject;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by pokor on 10.04.2016.
 */
public class WidgetServiceTest {

    private WidgetServiceImpl widgetService;

    @Before
    public void setup(){
        widgetService = new WidgetServiceImpl();
    }

    /**
     * Test if empty or null parameters returns null widget object
     */
    @Test
    public void getContent_emptyOrNull_NullWidgetObject() {
        Assert.assertTrue(widgetService.getContent("") instanceof NullWidgetObject);
        Assert.assertTrue(widgetService.getContent(null) instanceof NullWidgetObject);
    }

    /**
     * Test "NEWS" return news list
     */
    @Test
    public void getContent_news_newsList() {
    }
}
