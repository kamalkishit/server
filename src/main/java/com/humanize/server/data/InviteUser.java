package com.humanize.server.data;

import org.springframework.beans.factory.annotation.Required;

public class InviteUser {

    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    @Required
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}