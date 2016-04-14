package com.tpokora.projects.widget.rss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by pokor on 14.04.2016.
 */
public class RSSParserImplTest {

    private RSSParser rssParser;

    @Before
    public void setup() {
        rssParser = new RSSParserImpl();
    }

    /**
     * Test checks if function returns returns normalized RSS from TEST source
     */
    @Test
    public void parseRSS_test_parserRss() throws IOException, ParserConfigurationException {
        Document rss = rssParser.parseRSS("TEST");

        Assert.assertTrue("XML document nor parsed", rss.getDocumentElement().getNodeName().equals("rss"));
    }
}
