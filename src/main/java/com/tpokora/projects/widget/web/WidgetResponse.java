package com.tpokora.projects.widget.web;

import com.tpokora.projects.widget.model.WidgetAbstractModel;

import java.util.HashMap;

/**
 * Created by pokor on 07.04.2016.
 */
public class WidgetResponse {

    private HashMap<String, WidgetAbstractModel> content;

    public WidgetResponse() {
        this.content = new HashMap<String, WidgetAbstractModel>();
    }

    public HashMap<String, WidgetAbstractModel> getContent() {
        return content;
    }

    public void setContent(HashMap<String, WidgetAbstractModel> content) {
        this.content = content;
    }

    public void addContent(String name, WidgetAbstractModel element) {
        this.content.put(name, element);
    }

    public WidgetAbstractModel getContentByName(String name) {
        return this.content.get(name);
    }
}
