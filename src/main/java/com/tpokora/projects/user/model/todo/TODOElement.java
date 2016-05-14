package com.tpokora.projects.user.model.todo;

import java.util.Date;

/**
 * Created by pokor on 14.05.2016.
 */
public class TODOElement {

    private String content;
    private boolean checked;
    private Date date;

    public TODOElement() {}

    /**
     * Constructor with all parameters for creating very specific TODOElement
     * @param content
     * @param checked
     * @param date
     */
    public TODOElement(String content, boolean checked, Date date) {
        this.content = content;
        this.checked = checked;
        this.date = date;
    }

    /**
     * Constructor with content parameter with default parameters created using Client Request
     * @param content
     */
    public TODOElement(String content) {
        this.content = content;
        this.checked = false;
        this.date = new Date();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
