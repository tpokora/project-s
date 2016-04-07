package com.tpokora.projects.widget.web;

import com.tpokora.projects.widget.model.news.News;
import com.tpokora.projects.widget.model.news.NewsWrapper;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pokor on 07.04.2016.
 */
@RestController
@RequestMapping("/rest/widget")
public class WidgetController {

    private static Logger logger = Logger.getLogger(WidgetController.class);

    private WidgetResponse widgetResponse;

    @RequestMapping("/home")
    public ResponseEntity<WidgetResponse> get() {
        widgetResponse = new WidgetResponse();
        logger.info("Get widget data...");

        NewsWrapper newsWrapper = new NewsWrapper("Test News");
        News news1 = new News("News1", "http://news1.com");
        News news2 = new News("News2", "http://news2.com");
        News news3 = new News("News3", "http://news3.com");

        newsWrapper.addNews(news1);
        newsWrapper.addNews(news2);
        newsWrapper.addNews(news3);

        widgetResponse.addContent("news", newsWrapper);

        return new ResponseEntity<WidgetResponse>(widgetResponse, HttpStatus.OK);
    }

    @RequestMapping("/news/{source}")
    public ResponseEntity<WidgetResponse> getNewsBySource(@PathVariable("source") String source) {
        widgetResponse = new WidgetResponse();
        logger.info("Getting news from: " + source);


    }
}
