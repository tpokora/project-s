package com.tpokora.projects.widget.rss;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorTypes;

/**
 * Created by pokor on 16.04.2016.
 */
public class RSSError extends AbstractError {
    @Override
    public void setError(ErrorTypes type) {
        this.type = type;
        switch (type) {
            case RSS_NOT_EXISTS:
                this.text = "rss.not.exists";
                this.code = 404;
                break;
            default:
                this.code = 404;
                break;

        }
    }
}
