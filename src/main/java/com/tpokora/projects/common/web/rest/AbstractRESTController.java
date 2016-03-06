package com.tpokora.projects.common.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.web.RESTResponseWrapper;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by pokor on 14.02.2016.
 */
// TODO: Refactor to have restResponse for each controller
public abstract class AbstractRESTController {

    protected final Logger logger = Logger.getLogger(getClass());

    protected RESTResponseWrapper restResponse;

    public void addError(AbstractError abstractError) {
        restResponse.addError(abstractError);
    }

    public void addErrors(List<AbstractError> errors) {
        restResponse.addErrors(errors);
    }
}
