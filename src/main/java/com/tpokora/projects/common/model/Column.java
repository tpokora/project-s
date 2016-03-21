package com.tpokora.projects.common.model;

/**
 * Created by pokor on 21.03.2016.
 */
public class Column {
    private String name;
    private String type;

    public Column() {}

    public Column(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
