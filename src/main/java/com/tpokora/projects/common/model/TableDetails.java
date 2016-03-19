package com.tpokora.projects.common.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pokor on 16.03.2016.
 */
public class TableDetails {
    private String name;
    private Map<String, String> columns = new HashMap<String, String>();

    @Autowired
    private SessionFactory sessionFactory;

    public TableDetails() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColumns(String[] names, org.hibernate.type.Type[] columns) {
        for (int i = 0; i < names.length; i++) {
            this.columns.put(names[i], columns[i].getName());
        }
    }

    public Map<String, String> getColumns() {
        return columns;
    }
}
