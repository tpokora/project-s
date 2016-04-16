package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.AbstractWidgetModel;
import com.tpokora.projects.widget.model.rss.Feed;
import com.tpokora.projects.widget.model.rss.FeedMessage;
import com.tpokora.projects.widget.rss.RSSParser;
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

/**
 * Created by pokor on 16.04.2016.
 */
public class RSSService implements ContentService {

    @Autowired
    private RSSParser rssParser;

    @Override
    public AbstractWidgetModel getContent(String source) {

        Feed rssFeed = convertRSSXMLtoWidgetModel(source);

        return rssFeed;
    }

    private Feed convertRSSXMLtoWidgetModel(String source) {
        Feed feed = new Feed();
        try {
            Document rss = rssParser.parseRSS(source);

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

        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return feed;
    }
}
