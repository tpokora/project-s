package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.NullWidgetObject;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pokor on 10.04.2016.
 */
public class WidgetServiceTest {

    private static final Logger logger = Logger.getLogger(WidgetServiceTest.class);

    @Autowired
    private WidgetService widgetService;

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
}
