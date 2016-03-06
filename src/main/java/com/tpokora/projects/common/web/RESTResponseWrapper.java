package com.tpokora.projects.common.web;

import com.tpokora.projects.common.errors.AbstractError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pokor on 14.02.2016.
 */
public class RESTResponseWrapper {
    private HashMap<String, Object> content = new HashMap<String, Object>();

    private List<AbstractError> errors = new ArrayList<AbstractError>();

    public RESTResponseWrapper() {
    }

    public RESTResponseWrapper(String name, Object obj) {
        this.content.put(name, obj);
    }

    public RESTResponseWrapper(List<AbstractError> errors) {
        this.errors = errors;
    }

    public RESTResponseWrapper(AbstractError abstractError) {
        this.errors.add(abstractError);
    }

    public RESTResponseWrapper(String name, Object obj, List<AbstractError> errors) {
        this.content.put(name, obj);
        this.errors = errors;
    }

    public HashMap<String, Object> getContent() {
        return content;
    }

    public void addError(AbstractError abstractError) {
        this.errors.add(abstractError);
    }

    public void addErrors(List<AbstractError> errors) {
        this.errors = errors;
    }

    public List<AbstractError> getErrors() {
        return errors;
    }

    public void addContent(String name, Object obj) {
        this.content.put(name, obj);
    }

    public void clearResponse() {
        content.clear();
        errors.clear();
    }
}
