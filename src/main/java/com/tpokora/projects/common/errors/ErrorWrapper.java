package com.tpokora.projects.common.errors;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 13.02.2016.
 */
public class ErrorWrapper {
    private List<AbstractError> errors = new ArrayList<AbstractError>();

    public ErrorWrapper() {}

    public ErrorWrapper(AbstractError abstractError) {
        this.errors.add(abstractError);
    }

    public void addError(AbstractError abstractError) {
        this.errors.add(abstractError);
    }

    public List<AbstractError> getErrors() {
        return errors;
    }

}
