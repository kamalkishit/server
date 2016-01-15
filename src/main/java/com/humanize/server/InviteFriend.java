package com.humanize.server;

import org.springframework.beans.factory.annotation.Required;

public class InviteFriend {

    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    @Required
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}