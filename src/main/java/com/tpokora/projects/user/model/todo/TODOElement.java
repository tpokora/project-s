package com.tpokora.projects.user.model.todo;

import com.tpokora.projects.common.model.AbstractEntity;

import java.util.Date;

/**
 * Created by pokor on 14.05.2016.
 */
public class TODOElement extends AbstractEntity {

    private String content;
    private boolean checked;
    private Date date;

    public TODOElement() {}

    /**
     * Constructor with all parameters for creating very specific TODOElement
     * @param id
     * @param content
     * @param checked
     * @param date
     */
    public TODOElement(Integer id, String content, boolean checked, Date date) {
        super(id);
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
