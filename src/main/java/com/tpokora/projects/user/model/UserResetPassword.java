package com.tpokora.projects.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpokora.projects.common.model.AbstractEntity;
import com.tpokora.projects.common.utils.SessionIdentifierGenerator;
import com.tpokora.projects.user.model.nullobjects.NullUser;
import com.tpokora.projects.user.model.nullobjects.NullUserResetPassword;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pokor on 27.10.2016.
 */
@Entity
@Table(name = "RESET_PASSWORD_SESSION")
public class UserResetPassword extends AbstractEntity {

    @Column(name = "SESSIONID")
    private String sessionId;

    @Column(name = "TEMP_PASSWORD")
    @JsonIgnore
    private String tempPassword;

    @Column(name = "OLD_PASSWORD")
    @JsonIgnore
    private String oldPassword;

    @Column(name = "CREATE_TIME")
//    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date createTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    public UserResetPassword() {
        super();
    }

    public UserResetPassword(User user) {
        this.sessionId = SessionIdentifierGenerator.generateSessionID();
        this.createTime = new Date();
        this.user = user;
    }

    public UserResetPassword(String sessionId, String tempPassword, String oldPassword, Date createTime) {
        this.sessionId = sessionId;
        this.tempPassword = tempPassword;
        this.oldPassword = oldPassword;
        this.createTime = createTime;
    }

    public UserResetPassword(String sessionId, String tempPassword, String oldPassword, Date createTime, User user) {
        this.sessionId = sessionId;
        this.tempPassword = tempPassword;
        this.oldPassword = oldPassword;
        this.createTime = createTime;
        this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
