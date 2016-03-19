package com.tpokora.projects.common.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pokor on 16.03.2016.
 */
public class TableDetails {
    private String tableName;
    private Map<String, String> tableColumnsDescription = new HashMap<String, String>();

    @Autowired
    private SessionFactory sessionFactory;

    public TableDetails() {}

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableColumnsDescription(HashMap<String, String> tableColumnsDescription) {
        this.tableColumnsDescription = tableColumnsDescription;
    }

    public Map<String, String> getTableColumnsDescription() {
        return tableColumnsDescription;
    }
}
