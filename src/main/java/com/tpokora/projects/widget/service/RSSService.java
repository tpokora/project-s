package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.AbstractWidgetModel;
import com.tpokora.projects.widget.model.rss.Feed;
import com.tpokora.projects.widget.model.rss.FeedMessage;
import com.tpokora.projects.widget.rss.RSSParser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by pokor on 16.04.2016.
 */
public class RSSService implements ContentService {

    private final static Logger logger = Logger.getLogger(RSSService.class);

    @Autowired
    private RSSParser rssParser;

    @Override
    public List<String> getRSSSources() {
        return rssParser.getRSSSources();
    }

    @Override
    public AbstractWidgetModel getContent(String source) {
        try {
            Feed rssFeed = convertRSSXMLtoWidgetModel(source);
            rssFeed.setName("RSS");

            return rssFeed;
        } catch (NullPointerException e) {
            logger.error("RSS from: " + source + ", not available");
            return null;
        }
    }

    private Feed convertRSSXMLtoWidgetModel(String source) {
        Feed feed;
        try {

            Document rss = rssParser.parseRSS(source);

            feed = new Feed();

            XPathFactory xpf = XPathFactory.newInstance();
            XPath xp = xpf.newXPath();

            String sourceString = xp.evaluate("//rss/source/text()", rss.getDocumentElement());
            feed.setSource(sourceString);

            NodeList feedList = rss.getDocumentElement().getElementsByTagName("feed");

            for (int i = 0; i < feedList.getLength(); i++) {
                String title = xp.evaluate("title/text()", feedList.item(i));
                String link = xp.evaluate("link/text()", feedList.item(i));

                feed.addFeedMsg(new FeedMessage(title, link));
            }

        // TODO: Handle RSS Exceptions
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            logger.error("MalformedURLException");
            return null;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }

        return feed;
    }
}
