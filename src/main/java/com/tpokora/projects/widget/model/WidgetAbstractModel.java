package com.tpokora.projects.widget.model;

/**
 * Created by pokor on 07.04.2016.
 */
public abstract class WidgetAbstractModel {

    protected String name;

    public WidgetAbstractModel() {}

    public WidgetAbstractModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
