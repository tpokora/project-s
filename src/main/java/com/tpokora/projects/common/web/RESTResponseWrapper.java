package com.tpokora.projects.common.web;

import com.tpokora.projects.common.errors.AbstractError;
import com.tpokora.projects.common.errors.ErrorWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * Created by pokor on 14.02.2016.
 */
public class RESTResponseWrapper {
    private HashMap<String, Object> content = new HashMap<String, Object>();

    @Autowired
    private ErrorWrapper errors;

    public RESTResponseWrapper() {
    }

    public RESTResponseWrapper(String name, Object obj) {
        this.content.put(name, obj);
    }

    public RESTResponseWrapper(ErrorWrapper errors) {
        this.errors = errors;
    }

    public RESTResponseWrapper(AbstractError abstractError) {
        this.errors.addError(abstractError);
    }

    public RESTResponseWrapper(String name, Object obj, ErrorWrapper errors) {
        this.content.put(name, obj);
        this.errors = errors;
    }

    public HashMap<String, Object> getContent() {
        return content;
    }

    public void addError(AbstractError abstractError) {
        this.errors.addError(abstractError);
    }

    public void addErrors(ErrorWrapper errors) {
        this.errors = errors;
    }

    public ErrorWrapper getErrors() {
        return errors;
    }

    public void addContent(String name, Object obj) {
        this.content.put(name, obj);
    }

    public void clearResponse() {
        content.clear();
        errors.getErrors().clear();
    }
}
