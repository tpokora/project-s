package com.tpokora.projects.widget.service;

import com.tpokora.projects.widget.model.NullWidgetObject;
import com.tpokora.projects.widget.model.AbstractWidgetModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pokor on 10.04.2016.
 */
public class WidgetServiceImpl implements WidgetService {

    @Autowired
    ContentService newsService;

    @Override
    public AbstractWidgetModel getContent(String source) {

        AbstractWidgetModel content = new NullWidgetObject();

        if (source == null) {
            return content;
        }

        switch(source.toUpperCase()) {
            case "NEWS": {
                content = newsService.getContent();
                content.setName(source);
                break;
            }

            default:
                content = new NullWidgetObject();
        }

        return content;
    }
}
