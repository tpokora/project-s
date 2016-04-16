package com.tpokora.projects.widget.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorTypes;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import com.tpokora.projects.widget.model.AbstractWidgetModel;
import com.tpokora.projects.widget.model.rss.Feed;
import com.tpokora.projects.widget.service.ContentService;
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
    private AbstractError rssError;

    @Autowired
    private ContentService rssService;

    @RequestMapping("/home")
    public ResponseEntity<RESTResponseWrapper> get() {
        widgetResponse = new RESTResponseWrapper();

        return new ResponseEntity<RESTResponseWrapper>(widgetResponse, HttpStatus.OK);
    }

    @RequestMapping("/rss/{source}")
    public ResponseEntity<RESTResponseWrapper> getContentBySource(@PathVariable("source") String source) {
        widgetResponse = new RESTResponseWrapper();
        logger.info("Getting RSS source content: " + source);

        if (rssService.getContent(source) == null) {
            logger.error("No RSS source content: " + source);
            addErrorToResponse(ErrorTypes.RSS_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(widgetResponse, HttpStatus.NOT_FOUND);
        }

        widgetResponse.addContent("RSS", rssService.getContent(source));

        return new ResponseEntity<RESTResponseWrapper>(widgetResponse, HttpStatus.OK);
    }

    private void addErrorToResponse(ErrorTypes error) {
        rssError.setError(error);
        widgetResponse.addError(rssError);
    }
}
