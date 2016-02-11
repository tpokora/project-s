package com.tpokora.projects.home.web.rest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tomek on 2016-01-02.
 */
@RestController
@RequestMapping(value = "/rest")
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    private class HomeElement {
        private String title;
        private String header;
        private String content;

        public HomeElement() {
            this.title = "Template Spring Application";
            this.header = "Template Spring Application";
            this.content = "Template Spring Application";
        }

        public String getTitle() {
            return title;
        }

        public String getHeader() {
            return header;
        }

        public String getContent() {
            return content;
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<HomeElement> getHome() {
        logger.info(this.getClass().getSimpleName() + " retrieves data");
        HomeElement homeElement = new HomeElement();
        if (homeElement == null) {
            return new ResponseEntity<HomeElement>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<HomeElement>(homeElement, HttpStatus.OK);
    }
}
