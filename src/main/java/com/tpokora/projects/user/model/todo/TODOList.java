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

    public TODOElement getElementById(int id) {
        for (TODOElement element : todoElements) {
            if (element.getId() == id) {
                return element;
            }
        }

        return null;
    }

    public void removeElementByIndex(int index) {
        todoElements.remove(index);
    }

    public void removeElementById(int id) {
        for (TODOElement element : todoElements) {
            if (element.getId() == id) {
                todoElements.remove(id);
                break;
            }
        }
    }
}
