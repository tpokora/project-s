package com.tpokora.projects.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokor on 16.03.2016.
 */
public class TableDetails {
    private String name;
    private List<Column> columns = new ArrayList<Column>();

    public TableDetails() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColumns(String[] names, org.hibernate.type.Type[] columns) {
        for (int i = 0; i < names.length; i++) {
            this.columns.add(new Column(names[i], columns[i].getName()));
        }
    }

    public void addColumn(String name, String type) {
        columns.add(new Column(name, type));
    }

    public List<Column> getColumns() {
        return columns;
    }
}
