package com.tpokora.projects.widget.rss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

/**
 * Created by pokor on 14.04.2016.
 */
public class RSSParserImplTest {

    private RSSParser rssParser;
    private XPathFactory xpf;
    private XPath xp;

    @Before
    public void setup() {
        rssParser = new RSSParserImpl();
        xpf = XPathFactory.newInstance();
        xp = xpf.newXPath();
    }

    /**
     * Test checks if function returns returns normalized RSS from TEST source
     */
    @Test
    public void parseRSS_test_parserRss() throws ParserConfigurationException, TransformerException, SAXException, IOException, XPathExpressionException {
        Document rss = rssParser.parseRSS("test");

        String sourceText = xp.evaluate("//rss/source/text()", rss.getDocumentElement());

        Assert.assertTrue("Expected 'http://www.vogella.com': got: " + sourceText, sourceText.equals("http://www.vogella.com"));

        NodeList feedList = rss.getDocumentElement().getElementsByTagName("feed");

        for (int i = 0; i < feedList.getLength(); i++) {
            String title = xp.evaluate("title/text()", feedList.item(i));
            String link = xp.evaluate("link/text()", feedList.item(i));
            Assert.assertTrue(!title.equals("") && title != null);
            Assert.assertTrue(!link.equals("") && link != null);
        }

    }
}
