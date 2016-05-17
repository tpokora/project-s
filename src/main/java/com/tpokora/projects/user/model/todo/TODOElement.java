package com.tpokora.projects.user.model.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpokora.projects.common.model.AbstractEntity;
import com.tpokora.projects.user.model.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pokor on 14.05.2016.
 */
@Entity
@Table(name = "TODO")
public class TODOElement extends AbstractEntity {

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CHECKED")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean checked;

    @Column(name = "DATE")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date date;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    /**
     * @JsonManagedReference commented out due to error in handling reference when POSTed
     */
    private User user;

    public TODOElement() {}

    /**
     * Constructor with all parameters for creating very specific TODOElement
     * @param id
     * @param content
     * @param checked
     * @param date
     */
    public TODOElement(Integer id, String content, boolean checked, Date date, User user) {
        super(id);
        this.content = content;
        this.checked = checked;
        this.date = date;
        this.user = user;
    }

    /**
     * Constructor with content parameter with default parameters created using Client Request
     * @param content
     */
    public TODOElement(String content, User user) {
        this.content = content;
        this.checked = false;
        this.date = new Date();
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
