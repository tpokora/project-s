package com.tpokora.projects.articles.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorTypes;

/**
 * Created by pokor on 27.03.2016.
 */
public class ArticleError extends AbstractError {

    public ArticleError() {}

    @Override
    public void setError(ErrorTypes type) {
        this.type = type;
        switch (type) {
            case ARTICLE_NOT_EXISTS:
                this.text = "article.not.exists";
                this.code = 404;
                break;
            case ARTICLE_ALREADY_EXISTS:
                this.text = "article.exists";
                this.code = 422;
                break;
            default:
                this.code = 404;
                break;

        }
    }
}
