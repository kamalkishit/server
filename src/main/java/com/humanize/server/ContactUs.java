package com.humanize.server;

import org.springframework.beans.factory.annotation.Required;

public class ContactUs {

    private String emailId;
    private String subject;
    private String body;

    public String getEmailId() {
        return emailId;
    }

    @Required
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSubject() {
        return subject;
    }

    @Required
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    @Required
    public void setBody(String body) {
        this.body = body;
    }
}