package com.tpokora.projects.widget.rss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;

/**
 * Created by pokor on 14.04.2016.
 */
public class RSSParserImpl implements RSSParser {

    private final Logger logger = LoggerFactory.getLogger(RSSParserImpl.class);

    private URL url;
    private File xsl;

    public RSSParserImpl() {}

    @Override
    public Document parseRSS(String source) throws IOException, ParserConfigurationException {
        loadXSL("test");

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document rss = docBuilder.newDocument();
        Element ele = rss.createElement("rss");
        rss.appendChild(ele);

        return rss;
    }

    private void loadXSL(String name) {
        try {
            Resource resource = new ClassPathResource("/rss.parsers/" + name + ".xsl");
            xsl = resource.getFile();
        } catch (Exception e) {
            logger.error("Stylesheet '" + name + ".xsl' not found");
        }
    }
}
