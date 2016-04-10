package com.tpokora.projects.widget.web;

import com.tpokora.projects.common.web.RESTResponseWrapper;
import com.tpokora.projects.widget.model.news.NewsWrapper;
import com.tpokora.projects.widget.service.WidgetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private RESTResponseWrapper widgetResponse;

    @Autowired
    private WidgetService widgetService;

    @RequestMapping("/home")
    public ResponseEntity<RESTResponseWrapper> get() {
        widgetResponse = new RESTResponseWrapper();
        logger.info("Get widget data...");

        widgetResponse.addContent("news", new NewsWrapper());

        return new ResponseEntity<RESTResponseWrapper>(widgetResponse, HttpStatus.OK);
    }

    @RequestMapping("/{source}")
    public ResponseEntity<RESTResponseWrapper> getContentBySource(@PathVariable("source") String source) {
        widgetResponse = new RESTResponseWrapper();
        logger.info("Getting source content: " + source);

        widgetResponse.addContent("widgetContent", widgetService.getContent(source));

        return new ResponseEntity<RESTResponseWrapper>(widgetResponse, HttpStatus.OK);
    }
}
