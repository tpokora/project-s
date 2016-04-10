package com.tpokora.projects.widget.dao.news;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * Created by pokor on 10.04.2016.
 */
public class HTMLPageReaderTest {

    private static Logger logger = LoggerFactory.getLogger(HTMLPageReaderTest.class);

    private HTMLPageReader htmlPageReader;

    private String url;

    @Before
    public void setup() {
        url = "http://www.onet.pl";
    }

    @Test
    public void HTMLPageReader_getHtmlPage_getBodyElement() {
        htmlPageReader = new HTMLPageReader(url);

        Document doc = htmlPageReader.getHtmlPage();

        Element body =  doc.getDocumentElement();

        Assert.assertTrue(body.getNodeName().equals("body"));
    }
}
