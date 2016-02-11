package com.tpokora.projects.home.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Tomek on 2016-01-18.
 */
@Controller
public class HomeTemplateController {

    private static final Logger logger = Logger.getLogger(HomeTemplateController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomeTemplate() {
        logger.info(provideMsg("home"));
        return "template/home";
    }

    private String provideMsg(String templateName) {
        return this.getClass().getSimpleName() + templateName + " providing home template";
    }
}
