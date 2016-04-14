package com.tpokora.projects.widget.rss;

import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by pokor on 14.04.2016.
 */
public interface RSSParser {

    public Document parseRSS(String source) throws IOException, ParserConfigurationException;
}
