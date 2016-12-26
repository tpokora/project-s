package com.tpokora.projects.user.model;

/**
 * Created by pokor on 22.12.2016.
 */
public class UserMailResponse {

    private String to;
    private String status;

    public UserMailResponse() {}

    public UserMailResponse(String to, String status) {
        this.to = to;
        this.status = status;
    }

    public String getTo() {
        return to;
    }

    public String getStatus() {
        return status;
    }
}
