package com.tpokora.projects.articles;

import com.tpokora.projects.articles.service.ArticleService;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pokor on 26.03.2016.
 */
@RestController
@RequestMapping("/rest/article")
public class ArticleController {

    private static final Logger logger = Logger.getLogger(ArticleController.class);

    private static final String ARTICLE_RESPONSE_STRING = "articles";

    private static RESTResponseWrapper restResponse = new RESTResponseWrapper();

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> getAllArticles() {

        restResponse.addContent(ARTICLE_RESPONSE_STRING, articleService.getAllArticles());

        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

}
