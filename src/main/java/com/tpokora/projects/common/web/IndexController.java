package com.tpokora.projects.common.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomek on 2016-01-18.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        logger.info(this.getClass().getSimpleName() + " retrieves Index page");
        return "index";
    }
}
