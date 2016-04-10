package com.tpokora.projects.widget.model;

/**
 * Created by pokor on 07.04.2016.
 */
public abstract class AbstractWidgetModel {

    protected String name;

    public AbstractWidgetModel() {}

    public AbstractWidgetModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
