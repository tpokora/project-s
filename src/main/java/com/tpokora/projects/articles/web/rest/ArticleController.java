package com.tpokora.projects.articles.web.rest;

import com.tpokora.projects.articles.model.Article;
import com.tpokora.projects.articles.service.ArticleService;
import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorTypes;
import com.tpokora.projects.common.errors.RESTControllerError;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 26.03.2016.
 */
@RestController
@RequestMapping("/rest/article")
public class ArticleController implements RESTControllerError {

    private static final Logger logger = Logger.getLogger(ArticleController.class);

    private static final String ARTICLE_RESPONSE_STRING = "articles";

    private static RESTResponseWrapper restResponse = new RESTResponseWrapper();

    @Autowired
    AbstractError articleError;

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> getAllArticles() {
        restResponse.clearResponse();
        logger.info("Getting all articles...");

        if (articleService.getAllArticles() == null && articleService.getAllArticles().isEmpty()) {
            logger.info("NO ARTICLES FOUND");
            addErrorToRESTResponse(articleError, ErrorTypes.ARTICLE_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        restResponse.addContent(ARTICLE_RESPONSE_STRING, articleService.getAllArticles());

        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> getArticleById(@PathVariable("id") int id) {
        restResponse.clearResponse();
        logger.info("Getting article with id: " + id);

        if (articleService.getArticleById(id) == null) {
            logger.info("NO ARTICLES FOUND");
            addErrorToRESTResponse(articleError, ErrorTypes.ARTICLE_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        restResponse.addContent(ARTICLE_RESPONSE_STRING, articleService.getArticleById(id));

        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }


    @RequestMapping(value = "title/{title}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> getArticleByTitle(@PathVariable("title") String title) {
        restResponse.clearResponse();
        logger.info("Getting articles with title: " + title);

        if (articleService.getArticlesByTitle(title) == null || articleService.getArticlesByTitle(title).isEmpty()) {
            logger.info("NO ARTICLES FOUND");
            addErrorToRESTResponse(articleError, ErrorTypes.ARTICLE_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        restResponse.addContent(ARTICLE_RESPONSE_STRING, articleService.getArticlesByTitle(title));

        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> createNewArticle(@RequestBody Article article, UriComponentsBuilder ucb) throws Exception {
        restResponse.clearResponse();
        logger.info("Creating new article...");

        Article newArticle = article;

        try {
            newArticle = articleService.createOrUpdateArticle(newArticle);
        } catch (ConstraintViolationException e) {
            addErrorToRESTResponse(articleError, ErrorTypes.ARTICLE_ALREADY_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucb.path("/home").build().toUri());
        restResponse.addContent(ARTICLE_RESPONSE_STRING, newArticle);
        return new ResponseEntity<RESTResponseWrapper>(restResponse, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> updateArticle(@PathVariable("id") int id, @RequestBody Article article) {
        restResponse.clearResponse();
        System.out.println("Updating article: " + id);

        if (articleService.getArticleById(id) == null) {
            addErrorToRESTResponse(articleError, ErrorTypes.ARTICLE_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        restResponse.addContent(ARTICLE_RESPONSE_STRING, articleToArray(articleService.createOrUpdateArticle(article)));
        return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<RESTResponseWrapper> removeArticleById(@PathVariable("id") int id, UriComponentsBuilder ucb) {
        restResponse.clearResponse();
        logger.info("Removing article with id: " + id + " ...");
        Article deleteArticle = articleService.getArticleById(id);
        if (deleteArticle == null) {
            logger.error("Unable to delete user. User not exists");
            addErrorToRESTResponse(articleError, ErrorTypes.ARTICLE_NOT_EXISTS);
            return new ResponseEntity<RESTResponseWrapper>(restResponse, HttpStatus.NOT_FOUND);
        }

        articleService.deleteArticleById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucb.path("/home").build().toUri());
        return new ResponseEntity<RESTResponseWrapper>(headers, HttpStatus.NO_CONTENT);
    }

    @Override
    public void addErrorToRESTResponse(AbstractError error, ErrorTypes type) {
        error.setError(type);
        restResponse.addError(error);
    }

    private List<Article> articleToArray(Article article) {
        ArrayList<Article> articles = new ArrayList<Article>();
        articles.add(article);
        return articles;
    }
}
