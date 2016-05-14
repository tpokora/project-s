package com.tpokora.projects.user.model.todo;

import java.util.ArrayList;

/**
 * Created by pokor on 14.05.2016.
 */
public class TODOList {

    private ArrayList<TODOElement> todoElements;

    public TODOList() {
        todoElements = new ArrayList<TODOElement>();
    }

    public void createElement(String content) {
        todoElements.add(new TODOElement(content));
    }

    public TODOElement getElementByIndex(int index) {
        return todoElements.get(index);
    }

    public void removeElementByIndex(int index) {
        todoElements.remove(index);
    }
}
