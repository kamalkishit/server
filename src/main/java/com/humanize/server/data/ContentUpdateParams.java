package com.humanize.server.data;

public class ContentUpdateParams {

    private String contentId;
    private ContentUpdateOperations contentUpdateOperations;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public ContentUpdateOperations getContentUpdateOperations() {
        return contentUpdateOperations;
    }

    public void setContentUpdateOperations(ContentUpdateOperations contentUpdateOperations) {
        this.contentUpdateOperations = contentUpdateOperations;
    }
}