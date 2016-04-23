package com.tpokora.projects.widget.rss;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by pokor on 14.04.2016.
 */
public interface RSSParser {
    public List<String> getRSSSources();
    public Document parseRSS(String source) throws IOException, ParserConfigurationException, SAXException, TransformerException;
}
