package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.AbstractWidgetModel;

import java.util.List;

/**
 * Created by pokor on 16.04.2016.
 */
public interface ContentService {
    public List<String> getRSSSources();
    public AbstractWidgetModel getContent(String source);
}
