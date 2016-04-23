package com.tpokora.projects.widget.rss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by pokor on 14.04.2016.
 */
public class RSSParserImpl implements RSSParser {

    private final Logger logger = LoggerFactory.getLogger(RSSParserImpl.class);

    private Properties sources;

    private Transformer xsl;

    public RSSParserImpl() {
        try {
            loadProperties();
        } catch (IOException e) {
            logger.error("Can't load matcher file for parsing");
        }
    }

    @Override
    public List<String> getRSSSources() {
        ArrayList<String> sourcesNames = new ArrayList<>();
        Set<Object> keys = sources.keySet();

        sourcesNames.addAll(keys.stream().map(Object::toString).collect(Collectors.toList()));

        return sourcesNames;
    }

    @Override
    public Document parseRSS(String source) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        loadXSL(source);

        String url = sources.getProperty(source);

        if (url == null) {
            return null;
        }

        Document rss = getRSSFromUrl(sources.getProperty(source));
        DOMSource domSource = new DOMSource(rss);

        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);

        xsl.transform(domSource, result);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(new InputSource(new ByteArrayInputStream(stringWriter.toString().getBytes("utf-8"))));
    }

    private void loadXSL(String name) {
        try {
            Resource resource = new ClassPathResource("/rss/parsers/" + name + ".xsl");
            File xslFile = resource.getFile();
            Templates template = TransformerFactory.newInstance().newTemplates(new StreamSource(new FileInputStream(xslFile)));
            xsl = template.newTransformer();
        } catch (Exception e) {
            logger.error("Stylesheet '" + name + ".xsl' not found");
        }
    }

    private Document getRSSFromUrl(String urlString) throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line = "";
        StringBuffer xmlSource = new StringBuffer();

        while((line = bf.readLine()) != null) {
            xmlSource.append(line);
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(new InputSource(new ByteArrayInputStream(xmlSource.toString().getBytes("utf-8"))));
    }

    private void loadProperties() throws IOException {
        Resource resource = new ClassPathResource("/rss/matcher.properties");
        sources = PropertiesLoaderUtils.loadProperties(resource);
    }
}
