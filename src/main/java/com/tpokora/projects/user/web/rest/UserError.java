package com.tpokora.projects.user.web.rest;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorTypes;

/**
 * Created by pokor on 14.02.2016.
 */
public class UserError extends AbstractError {

    public UserError() {}

    public UserError(ErrorTypes error) {
        super(error);
    }

    @Override
    public void setError(ErrorTypes type) {
        this.type = type;
        switch (type) {
            case USER_NOT_EXISTS:
                this.text = "user.not.exists";
                this.code = 404;
                break;
            case USER_ALREADY_EXISTS:
                this.text = "user.exists";
                this.code = 422;
                break;
            default:
                this.code = 404;
                break;

        }
    }
}
