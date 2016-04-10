package com.tpokora.projects.widget.dao.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pokor on 10.04.2016.
 */
public class HTMLPageReader {

    private static Logger logger = LoggerFactory.getLogger(HTMLPageReader.class);

    private URL url;

    private Document htmlPage;

    public HTMLPageReader(String urlString) {
        convertHtmlToXML(urlString);
    }

    public Document getHtmlPage() {
        return htmlPage;
    }

    private void convertHtmlToXML(String urlString) {
        try {
            url = new URL(urlString);

            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));

                String xmlString = "";
                String line = "";

                while ((line = bf.readLine()) != null) {
                    xmlString += line;
                }

                htmlPage = convertStringToDocument(xmlString);

            } catch (IOException ioe) {
                logger.error("Cannot get URL content");
            }

        } catch (MalformedURLException e) {
            logger.error("Bad url format");
        }
    }

    private Document convertStringToDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            xmlString = getBodyElement(xmlString);
            Document output = builder.parse(new InputSource(new StringReader(xmlString)));

            return output;
        } catch (ParserConfigurationException pce) {
            logger.error("Error while parsing string to XML");
        } catch (SAXException e) {
            logger.error("SAX Error");
        } catch (IOException e) {
            logger.error("");
        }

        return null;
    }

    // TODO: fix pattern for getting body element
    private String getBodyElement(String input) {
        Pattern pattern = Pattern.compile("<body.*>.*</body>");
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group(0) : "<body></body>";
    }

}
