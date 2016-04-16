package com.tpokora.projects.common.errors;

/**
 * Created by pokor on 27.03.2016.
 */
public interface RESTControllerError {
    public void addErrorToRESTResponse(AbstractError error, ErrorTypes type);
}
