package com.tpokora.projects.email.model;

/**
 * Created by pokor on 22.12.2016.
 */
public class EmailResponse {

    private String to;
    private String status;

    public EmailResponse() {}

    public EmailResponse(String to, String status) {
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
